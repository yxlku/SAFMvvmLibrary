package com.deti.brand.demand.create

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_EXPRESS_LIST
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_COLORS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_SIZE_COUNT
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_STYLE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.UPLOAD_FILE
import com.deti.brand.demand.create.entity.DemandStyleEntity
import com.deti.brand.demand.create.item.file.ItemUploadFileEntity
import com.deti.brand.demand.create.item.form.ItemFormChooseEntity
import com.deti.brand.demand.create.item.form.ItemFormChooseType
import com.safmvvm.binding.command.BindingConsumer
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.sql.Types


@FlowPreview
@ExperimentalCoroutinesApi
class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {

    /** 服务类型*/
    var mServiceType = ObservableField<String>()
    /** 对应服务*/
    var mServiceProduce = ObservableField<String>()

    /** 快递选择信息 -- 最后的数据信息 */
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
    var mSizeType = ""
    /** 交期*/
    var mTime = ""

    /** 单价*/
    var mPrice = ObservableField<String>()
    var consumerPrice = BindingConsumer<String> { t -> mPrice.set(t)  }



    /**
     * 服务类型
     */
    fun clickServiceType(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("0", "包工包料"),
            BaseSingleChoiceEntity("1", "纯加工"),
        )
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_TYPE, datas)
    }
    /**
     * 对应服务
     */
    fun clickServiceProduce(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("0", "打版 + 生产"),
            BaseSingleChoiceEntity("1", "仅生产"),
        )
        var pos = datas.indexOf(mServiceProduce.get())
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_PRODUCE, datas)
    }
    /**
     * 地址弹窗
     */
    fun clickAddress(view: View){
//        LiveDataBus.send(DIALOG_TIP_ADDRESS, Pair(view,"浙江省杭州市余杭区临平区余杭商会大厦C座 联系电话：123 4567 8910"))
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
        LogUtil.d("请求了")
        launchRequest {
            mModel.requestStyleInfo()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
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
                                            var four = TypesViewDataBean(it.id, it.name, null)
                                            levelFour.add(four)
                                        }
                                        var three = TypesViewDataBean(it.id, it.name, levelFour)
                                        levelThree.add(three)
                                    }
                                    var two = TypesViewDataBean(it.id, it.name, levelThree)
                                    levelTwo.add(two)
                                }
                                var one = TypesViewDataBean(it.id, it.name, levelTwo)
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

    /** 选择颜色*/
    fun formClickChooseColor(view: View, entity: ItemFormChooseEntity){
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
        FORM_SIZE_COUNT
    }

    /** 尺码类型*/
    fun formClickChooseSizeType(view: View, entity: ItemFormChooseEntity){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("0", "数字码"),
            BaseSingleChoiceEntity("1", "字母码")
        )
        LiveDataBus.send(CreateDemandFragment.FORM_SIZE_TYPE, Pair(datas, entity))
    }

    /** 交期*/
    fun formClickChooseTime(view: View, entity: ItemFormChooseEntity){
        LiveDataBus.send(CreateDemandFragment.FORM_TIME, entity)
    }
}