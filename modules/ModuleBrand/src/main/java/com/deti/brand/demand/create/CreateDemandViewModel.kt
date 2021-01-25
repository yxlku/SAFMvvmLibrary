package com.deti.brand.demand.create

import android.app.Application
import android.view.View
import androidx.collection.ArraySet
import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_CHOOSE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_EXPRESS_LIST
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_TIP_ADDRESS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_COLORS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_SIZE_COUNT
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_STYLE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.UPLOAD_FILE
import com.deti.brand.demand.create.entity.DemandStyleEntity
import com.deti.brand.demand.create.item.demandtype.ItemDeamandTypeChooseEntity
import com.deti.brand.demand.create.item.file.ItemUploadFileEntity
import com.deti.brand.demand.create.item.form.ItemFormChooseEntity
import com.deti.brand.demand.create.item.form.ItemFormChooseType
import com.safmvvm.binding.command.BindingConsumer
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonFindSizeDataBean
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorDataBean
import com.test.common.ui.popup.color.DemandColorListDataBean
import com.test.common.ui.popup.color.DemandColorListEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.Exception
import java.lang.StringBuilder
import java.sql.Types
import java.util.*


@FlowPreview
@ExperimentalCoroutinesApi
class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {

    /** 类型选择*/
    var mChooseTypes = arrayListOf<BaseMultipleChoiceEntity>()

    /** 服务类型*/    //TODO 统一改到Item中赋值
    var mServiceProduce = ObservableField<BaseSingleChoiceEntity>()
    /** 对应服务*/
    var mServiceType = ObservableField<BaseSingleChoiceEntity>()

    /** 样衣 - 快递选择信息 -- 最后的数据信息 */
    var mExpressSingleChoiceEntity = ObservableField<BaseSingleChoiceEntity>()
    /** 快递单号*/
    var mExpressNum = ObservableField<String>()
    /** 快递单号输入监听*/
    var consumerExpressNum = BindingConsumer<String> { t -> mExpressNum.set(t) }

    /** 面料文件地址*/
    var mFilePathFabric = ""
    /** 制版文件地址*/
    var mFilePathPlate = ""

    /** 款式分类一*/
    var mStyleList = arrayListOf<TypesViewDataBean?>()

    /** 尺码类型*/
    /** 全局尺码组数据*/
    var cFindSizeEntityData: CommonFindSizeEntity? = null
    /** 选中的尺码类型*/
    var mSizeTypeData: CommonFindSizeDataBean? = null

    /** 选择的颜色*/
    var mSelectColorDatas: ArrayList<DemandColorDataBean>? = null

    /** 交期*/
    var mTime: String? = null

    /** 单价*/
    var mPrice = ObservableField<String>()
    var consumerPrice = BindingConsumer<String> { t -> mPrice.set(t)  }

    /** 颜色-尺码-数量*/
    var mColorSizeCountDatas = arrayListOf<CommonColorEntity>()


    /**
     * 类型选择
     */
    fun clickChooseTypeDialog(view: View, entity: ItemDeamandTypeChooseEntity){
        LiveDataBus.send(DIALOG_CHOOSE_TYPE, entity)
    }
    /**
     * 服务类型
     */
    fun clickServiceProduce(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("FOB", "包工包料"),
            BaseSingleChoiceEntity("CMT", "纯加工"),
        )
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_PRODUCE, datas)
    }
    /**
     * 对应服务
     */
    fun clickServiceType(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("SAMPLE_BULK", "打版 + 生产"),
            BaseSingleChoiceEntity("BULK", "仅生产"),
        )
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_TYPE, datas)
    }
    /**
     * 地址弹窗
     */
    fun clickAddress(view: View){
        LiveDataBus.send(DIALOG_TIP_ADDRESS, Pair(view,"浙江省杭州市余杭区临平区余杭商会大厦C座 联系电话：123 4567 8910"))
        LogUtil.d("面料地址：${mFilePathFabric}")
        LogUtil.d("制版地址：${mFilePathPlate}")
    }
    /**
     * 获取快递列表
     */
    fun clickRequestExpressList(view: View) {
        LogUtil.d("快递单号：${mExpressNum.get()}")
        launchRequest {
            mModel.requestExpressList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        var baseSingleChoiceEntitys = arrayListOf<BaseSingleChoiceEntity>()
                        it?.data?.dataDictionaryList?.forEach { dataDictionaryEntity ->
                            baseSingleChoiceEntitys.add(
                                BaseSingleChoiceEntity(
                                    dataDictionaryEntity.id,
                                    dataDictionaryEntity.text
                                )
                            )
                        }
                        if(baseSingleChoiceEntitys.size > 0){
                            var pos = baseSingleChoiceEntitys.indexOf(mExpressSingleChoiceEntity.get())
                            LiveDataBus.send(DIALOG_EXPRESS_LIST, Pair(baseSingleChoiceEntitys, pos))
                        }
                    }
                )
        }
    }

    /**
     * 上传文件
     */
    fun clickUploadFile(view: View, entity: ItemUploadFileEntity){
        LiveDataBus.send(UPLOAD_FILE, entity)
    }

    /**
     * 表单 - 选择
     */
    fun clickFormChoose(view: View, entity: ItemFormChooseEntity){
        when (entity.tag) {
            ItemFormChooseType.CHOOSE_STYLE -> formClickChooseStyle(view, entity) //款式选择
            ItemFormChooseType.CHOOSE_SIZE_TYPE -> formClickChooseSizeType(view, entity) //尺码类型
            ItemFormChooseType.CHOOSE_TIME -> formClickChooseTime(view, entity) //尺码类型
            ItemFormChooseType.CHOOSE_COLOR -> formClickChooseColor(view, entity) //选择颜色
            ItemFormChooseType.CHOOSE_SIZE_COUNT -> formClickChooseSizeCount(view, entity) //选择尺码数量
        }
    }


    /** 款式类型 TODO 数据转换待处理*/
    fun formClickChooseStyle(view: View, entity: ItemFormChooseEntity){
        launchRequest {
            mModel.requestStyleInfo()
                .flowDataDeal(
                    loadingModel = LoadingModel.LOADING,
                    onSuccess = {
                        it?.data?.apply {
                            var treeEntity = TypesTreeViewEntity()
                            var levelOne = arrayListOf<TypesViewDataBean>()

                            this.tree?.forEach {
                                var levelTwo = arrayListOf<TypesViewDataBean>()
                                it.children?.forEach {
                                    var levelThree = arrayListOf<TypesViewDataBean>()
                                    it.children?.forEach {
                                        var levelFour = arrayListOf<TypesViewDataBean>()
                                        it.children?.forEach {
                                            var four = TypesViewDataBean(it.id, it.name, it.code, null)
                                            levelFour.add(four)
                                        }
                                        var three = TypesViewDataBean(it.id, it.name, it.code, levelFour)
                                        levelThree.add(three)
                                    }
                                    var two = TypesViewDataBean(it.id, it.name, it.code, levelThree)
                                    levelTwo.add(two)
                                }
                                var one = TypesViewDataBean(it.id, it.name, it.code, levelTwo)
                                levelOne.add(one)
                            }

                            treeEntity.childer = levelOne
                            LiveDataBus.send(FORM_STYLE_TYPE, Pair(treeEntity, entity))
                        }
                        LogUtil.d("请求成功")
                    },
                    onFaile = {code: String, msg: String ->
                        LogUtil.d("请求失败$code - $msg")
                    },
                    onError = {
                        LogUtil.exception(ex = it)
                    }

                )
        }

    }

    /** 尺码类型*/
    fun formClickChooseSizeType(view: View, entity: ItemFormChooseEntity){
        //1、先判断是否选了款式分类
        if (mStyleList == null || mStyleList.size < 3) {
            ToastUtil.showShortToast("请选择款式分类")
            return
        }
        //2、获取尺码组数据
//        if (cFindSizeEntityData == null) {
            launchRequest {
                mModel.requestFindSize(mStyleList)
                    .flowDataDeal(
                        loadingModel = LoadingModel.LOADING,
                        onSuccess = {
                            cFindSizeEntityData = it?.data
                            showChooseSizeTypeShow(view, entity)
                        }
                    )
//            }
        }
        showChooseSizeTypeShow(view, entity)
    }

    /** 选择颜色*/
    fun formClickChooseColor(view: View, entity: ItemFormChooseEntity){
        if (cFindSizeEntityData == null || mSizeTypeData == null) {
            ToastUtil.showShortToast("请选择尺码类型")
            return
        }
        launchRequest {
            mModel.requestColorsList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        it?.data.apply {
                            LiveDataBus.send(FORM_COLORS, Pair(entity, this))
                        }
                    }
                )
        }

    }



    /** 选择尺码数量*/
    fun formClickChooseSizeCount(view: View, entity: ItemFormChooseEntity){
        if (mSelectColorDatas == null || mSelectColorDatas?.size!! <= 0) {
            ToastUtil.showShortToast("请选择颜色")
            return
        }

        var firstNode = arrayListOf<FirstNodeEntity>()
        mSelectColorDatas?.forEach {
            var secondNode = arrayListOf<BaseNode>()
            mSizeTypeData?.sizeRangeList?.forEach {size ->
                secondNode.add(SecondNodeEntity(0, size, 0, it.name))
            }
            firstNode.add(
                FirstNodeEntity(
                    it.id,
                    it.name,
                    0,
                    it.code,
                    secondNode
                )
            )
        }
        LiveDataBus.send(FORM_SIZE_COUNT, Pair(firstNode, entity))
    }

    /** 尺码类型：弹窗*/
    fun showChooseSizeTypeShow(view: View, entity: ItemFormChooseEntity){
        //弹窗
        cFindSizeEntityData?.apply {
            var chooseData = arrayListOf<BaseSingleChoiceEntity>()
            this.list.forEach { bean ->
                chooseData.add(BaseSingleChoiceEntity(bean.id, bean.label))
            }
            LiveDataBus.send(CreateDemandFragment.FORM_SIZE_TYPE, Pair(chooseData, entity))
        }
    }


    /** 交期*/
    fun formClickChooseTime(view: View, entity: ItemFormChooseEntity){
        LiveDataBus.send(CreateDemandFragment.FORM_TIME, entity)
    }

    /**
     * 提交需求
     */
    fun clickPlaceOrder(view: View){
        var sb = StringBuilder()
        mChooseTypes.forEach {
            sb.append("id: ${it.id} - text: ${it.text}")
        }
        LogUtil.d(sb.toString())
        var testProvideList = arrayListOf(
            "PICTURE",//图片
            "SAMPLE",//样衣
            "FABRIC",//面料信息
            "LAYOUT",//设计稿
            "PRODUCTION_STANDARD" //制版文件
        )

        var testPicList = arrayListOf(
            "111",
            "22222",
            "",
        )
        var testRem = ""
        launchRequest {
            try {
                mModel.requestDemandSubmit(
                    testProvideList,
                    mServiceType.get()?.id,
                    mServiceProduce.get()?.id,
                    mFilePathFabric,
                    mExpressSingleChoiceEntity.get()?.id,
                    mExpressNum.get(),
                    mFilePathPlate,
                    testPicList[0],
                    testPicList[1],
                    testPicList,
                    mStyleList[0]?.code,
                    mStyleList[1]?.code,
                    mStyleList[2]?.code,
                    mStyleList[3]?.code,
                    mColorSizeCountDatas,
                    mPrice.get()?.toDouble(),
                    mTime,
                    testRem
                ).flowDataDeal(
                    loadingModel = LoadingModel.LOADING,
                    onSuccess = {
                        ToastUtil.showShortToast("需求提交成功")
                    }
                )
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }

    /**
     * 提交前的限制 和 提醒
     */
    fun submitLimit(): Boolean{
        var isAllowSubmit = true

        return isAllowSubmit
    }
}