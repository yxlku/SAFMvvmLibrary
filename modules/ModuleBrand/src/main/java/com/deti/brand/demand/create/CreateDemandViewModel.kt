package com.deti.brand.demand.create

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_CHOOSE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_EXPRESS_LIST
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_TIP_ADDRESS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_COLORS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_SIZE_COUNT
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_STYLE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.UPLOAD_FILE
import com.deti.brand.demand.create.item.demandtype.ItemDeamandTypeChooseEntity
import com.deti.brand.demand.create.item.express.ItemExpressEntity
import com.deti.brand.demand.create.item.file.ItemUploadFileEntity
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum
import com.deti.brand.demand.create.item.form.ItemFormChooseEntity
import com.deti.brand.demand.create.item.form.ItemFormChooseType
import com.deti.brand.demand.create.item.form.ItemFormInputEntity
import com.deti.brand.demand.create.item.grouptitle.ItemGroupTitleEntity
import com.deti.brand.demand.create.item.personinfo.ItemPersonalInfoEntity
import com.deti.brand.demand.create.item.pic.ItemPicChooseEntity
import com.deti.brand.demand.create.item.placeorder.ItemPlaceOrderEntity
import com.deti.brand.demand.create.item.remark.ItemRemarkEntity
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.safmvvm.binding.command.BindingConsumer
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.bus.putValue
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonFindSizeDataBean
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.ui.popup.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorDataBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.Exception
import java.util.*


class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {

    //完善个人信息
    var itemEntityPersonal = ItemPersonalInfoEntity()

    //类型选择
    var itemEntityTypeChoose = ItemDeamandTypeChooseEntity()

    //图片
    var itemEntityPic = ItemPicChooseEntity()
    //面料信息
    var itemEntityFabric = ItemUploadFileEntity(ItemUploadFileEnum.FILE_FABRIC, "请上传面料信息", "(选填)", "上传面料信息")
    //样衣
    var itemEntitySamplelothes = ItemExpressEntity()
    //制版文件
    var itemEntityPlate = ItemUploadFileEntity(ItemUploadFileEnum.FILE_PLATE, "请上传制版文件", "(选填)", "上传制版文件")

    /** 款式分类*/
    var itemEntityFormStyle = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_STYLE,"款式分类", false, "请选择款式分类")
    //尺码类型
    var itemEntityFormSizeType = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_TYPE, "尺码类型", false, "请选择所需要的尺码")
    //颜色选择
    var itemEntityFormColor = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_COLOR, "颜色选择", false, "可设置多个颜色")
    //尺码数量
    var itemEntityFormSizeCount = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_COUNT, "尺码数量", false, "可设置多个")

    /** 单价*/
    var itemEntityInputPrice = ItemFormInputEntity("预算单价", false, "请输入价格", unitText = "元")
    /** 交期*/
    var itemEntityFormTime = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_TIME, "设置交期", false, "交期最低14天")
    /** 备注*/
    var itemEntityInputRemark = ItemRemarkEntity()
    /** 下单按钮*/
    var itemEntityPlaceOrder = ItemPlaceOrderEntity()

    var itemListEntitys = arrayListOf<Any>()
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //初始化列表 - 列表添加顺序为显示属性
        itemListEntitys = arrayListOf(
            /** 个人信息*/
            itemEntityPersonal,

            //选择需求类型
            itemEntityTypeChoose,

            //服务
            ItemTransparentLineEntity(),
            itemEntityService,

            //样衣
            itemEntitySamplelothes,
            //图片
            itemEntityPic,
            //面料
            itemEntityFabric,
            //制版文件
            itemEntityPlate,

            //分组标题 //请填写服务详细信息
            ItemGroupTitleEntity("请填写服务详细信息"),
            //款式分类
            ItemGrayLineEntity(),
            itemEntityFormStyle,

            //尺码类型
            ItemGrayLineEntity(),
            itemEntityFormSizeType,
            //颜色选择
            ItemGrayLineEntity(),
            itemEntityFormColor,
            //尺码数量
            ItemGrayLineEntity(),
            itemEntityFormSizeCount,

            //单价
//            ItemTransparentLineEntity(),
//            itemEntityInputPrice,

            //设置交期
            ItemGrayLineEntity(),
            itemEntityFormTime,

            //备注
//            ItemTransparentLineEntity(),
//            itemEntityInputRemark,

            //下单按钮
            ItemTransparentLineEntity(),
            itemEntityPlaceOrder,
            ItemTransparentLineEntity(),
        )
    }

    /** 样衣 - 快递选择信息 -- 最后的数据信息 */
    var mExpressSingleChoiceEntity = ObservableField<BaseSingleChoiceEntity>()

    /**
     * 获取快递列表
     */
    fun clickRequestExpressList(view: View) {
        launchRequest {
            mModel.requestExpressList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        var baseSingleChoiceEntitys = arrayListOf<BaseSingleChoiceEntity>()
                        it?.data?.dataDictionaryList?.forEach { dataDictionaryEntity ->
                            baseSingleChoiceEntitys.add(
                                BaseSingleChoiceEntity(
                                    dataDictionaryEntity.code,
                                    dataDictionaryEntity.text
                                )
                            )
                        }
                        if(baseSingleChoiceEntitys.size > 0){
                            //TODO 需要加载一次，不能每次都请求
                            var pos = baseSingleChoiceEntitys.indexOf(mExpressSingleChoiceEntity.get())
                            DIALOG_EXPRESS_LIST.putValue(Pair(baseSingleChoiceEntitys, pos))
                        }
                    }
                )
        }
    }


















    /** 款式分类一*/
    var mStyleList = arrayListOf<TypesViewDataBean?>()

    /** 尺码类型*/
    /** 全局尺码组数据*/
    var cFindSizeEntityData: CommonFindSizeEntity? = null
    /** 选中的尺码类型*/
    var mSizeTypeData: CommonFindSizeDataBean? = null

    /** 选择的颜色*/
    var mSelectColorDatas: ArrayList<DemandColorDataBean> = arrayListOf()

    /** 交期*/
    var mTime: String? = null

    /** 单价*/
    var mPrice = ObservableField<String>()
    var consumerPrice = BindingConsumer<String> { t -> mPrice.set(t)  }

    /** 颜色-尺码-数量*/
    var mColorSizeCountDatas = arrayListOf<CommonColorEntity>()
    var mSizeId: String? = ""

    /** 备注*/
    var mRemark = ObservableField<String>()
    var consumerRemark = BindingConsumer<String> { t -> mRemark.set(t) }

    /** 图片列表*/
    var mPicListDatas = arrayListOf<String>("", "", "", "", "")

    //服务
    var itemEntityService = ItemServiceEntity()

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
//        showChooseSizeTypeShow(view, entity)
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
        if (mSelectColorDatas == null || mSelectColorDatas.size <= 0) {
            ToastUtil.showShortToast("请选择颜色")
            return
        }

        var firstNode = arrayListOf<FirstNodeEntity>()
        mSelectColorDatas.forEach {
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
//        LogUtil.d("快递单号：${itemEntitySamplelothes.mExpressNum.get()}")
//        LogUtil.d("服务类型：${itemEntityService.mServiceType.get()?.text}, 对应服务：${itemEntityService.mServiceProduce.get()?.text}")
//        var sb = StringBuilder()
//        itemEntityTypeChoose.mChooseTypes.forEach {
//            sb.append(it.id).append("、")
//        }
//        LogUtil.d("选择类型：${sb}")
        
        LogUtil.d("面料信息：${itemEntityFabric.filePath.get()}")
        LogUtil.d("制版信息：${itemEntityPlate.filePath.get()}")




        //提交前的限制 和 提醒
//        if(mChooseTypes.size <= 0){
//            ToastUtil.showShortToast("请先选择需求类型")
//            return
//        }
//        if(mServiceProduce.get() == null || mServiceProduce.get()?.id.isNullOrEmpty()){
//            ToastUtil.showShortToast("请先选择服务类型")
//            return
//        }
//        if(mServiceType.get() == null || mServiceType.get()?.id.isNullOrEmpty()){
//            ToastUtil.showShortToast("请先选择对应服务")
//            return
//        }
        //TODO 正面图片判断

        if(mStyleList.size <= 0){
            ToastUtil.showShortToast("请添加款式分类")
            return
        }
        if (mSizeTypeData == null) {
            ToastUtil.showShortToast("请选择尺码类型")
            return
        }
        if (mSelectColorDatas.size <= 0) {
            ToastUtil.showShortToast("请选择颜色")
            return
        }
        if(mColorSizeCountDatas.size <= 0){
            ToastUtil.showShortToast("请添加尺码数量")
            return
        }

        LogUtil.d("钱：${mPrice.get()}，备注：${mRemark.get()}")
//        if (mPrice.get()?.isEmpty()) {
//            ToastUtil.showShortToast("请输入预算单价")
//            return
//        }
//        if (mTime.isNullOrEmpty()) {
//            ToastUtil.showShortToast("请设置交期")
//            return
//        }

        launchRequest {
            try {
                mModel.requestDemandSubmit(
                    this@CreateDemandViewModel,
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

}