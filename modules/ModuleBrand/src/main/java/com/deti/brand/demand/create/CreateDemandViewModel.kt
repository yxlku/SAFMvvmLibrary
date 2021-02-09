package com.deti.brand.demand.create

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_EXPRESS_LIST
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_COLORS
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_SIZE_COUNT
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_SIZE_TYPE
import com.deti.brand.demand.create.CreateDemandFragment.Companion.FORM_STYLE_TYPE
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
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorDataBean
import com.test.common.ui.popup.color.DemandColorListEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {

    //完善个人信息
    var itemEntityPersonal = ItemPersonalInfoEntity()

    //类型选择
    var itemEntityTypeChoose = ItemDeamandTypeChooseEntity()
    //服务
    var itemEntityService = ItemServiceEntity()

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
            itemEntityPersonal.apply {
                isShow = true
            },

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

    /** 尺码类型*/
    /** 全局尺码组数据*/
    var cFindSizeEntityData: CommonFindSizeEntity? = null


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

    /**
     * 获取快递列表
     */
    fun clickRequestExpressList(view: View) {
        launchRequest {
            mModel.requestExpressList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        it?.data?.dataDictionaryList?.apply {
                            //TODO 这里应该不用pos
                            var pos = this.indexOf(itemEntitySamplelothes.mExpressSingleChoiceEntity.get())
                            DIALOG_EXPRESS_LIST.putValue(Pair(this as ArrayList<BaseSingleChoiceEntity>, pos))
                        }
                    }
                )
        }
    }


    /** 款式类型*/
    fun formClickChooseStyle(view: View, entity: ItemFormChooseEntity) {
        launchRequest {
            mModel.requestStyleInfo()
                .flowDataDeal(
                    loadingModel = LoadingModel.LOADING,
                    onSuccess = {
                        FORM_STYLE_TYPE.putValue(it?.data as TypesTreeViewEntity)
                        LogUtil.d("请求成功")
                    },
                    onFaile = { code: String, msg: String ->
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
        if (itemEntityFormStyle.mStyleList == null || itemEntityFormStyle.mStyleList.size < 3) {
            ToastUtil.showShortToast("请选择款式分类")
            return
        }
        //2、获取尺码组数据
        launchRequest {
            mModel.requestFindSize(itemEntityFormStyle.mStyleList)
                .flowDataDeal(
                    loadingModel = LoadingModel.LOADING,
                    onSuccess = {
                        cFindSizeEntityData = it?.data
                        cFindSizeEntityData?.list?.apply {
                            FORM_SIZE_TYPE.putValue(this as ArrayList<BaseSingleChoiceEntity>)
                        }
                    }
                )
        }
    }

    /** 选择颜色*/
    fun formClickChooseColor(view: View, entity: ItemFormChooseEntity){
        if (cFindSizeEntityData == null || itemEntityFormSizeType.mSizeTypeData == null) {
            ToastUtil.showShortToast("请选择尺码类型")
            return
        }
        launchRequest {
            mModel.requestColorsList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        //显示颜色
                        it?.data.apply {
                            FORM_COLORS.putValue(this as DemandColorListEntity)
                        }
                    }
                )
        }
    }
    /** 选择尺码数量*/
    fun formClickChooseSizeCount(view: View, entity: ItemFormChooseEntity){
        if (itemEntityFormColor.mSelectColorDatas == null || itemEntityFormColor.mSelectColorDatas.size <= 0) {
            ToastUtil.showShortToast("请选择颜色")
            return
        }
        //显示的颜色和尺寸布局数据 - [颜色A-尺码组...-数量] - [颜色A-尺码组...-数量]...
        var firstNode = arrayListOf<FirstNodeEntity>()
        itemEntityFormColor.mSelectColorDatas.forEach {
            var secondNode = arrayListOf<BaseNode>()
            itemEntityFormSizeType.mSizeTypeData?.sizeRangeList?.forEach {size ->
                secondNode.add(SecondNodeEntity(0, size, 0, it.name))
            }
            firstNode.add(
                FirstNodeEntity(it.id,  it.name, 0, it.code, secondNode)
            )
        }
        FORM_SIZE_COUNT.putValue(firstNode)
    }

















    /** 交期*/
    var mTime: String? = null

    /** 单价*/
    var mPrice = ObservableField<String>()


    /** 备注*/
    var mRemark = ObservableField<String>()

    /** 图片列表*/
    var mPicListDatas = arrayListOf<String>("", "", "", "", "")









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

//        if(mStyleList.size <= 0){
//            ToastUtil.showShortToast("请添加款式分类")
//            return
//        }
//        if (mSizeTypeData == null) {
//            ToastUtil.showShortToast("请选择尺码类型")
//            return
//        }
//        if (mSelectColorDatas.size <= 0) {
//            ToastUtil.showShortToast("请选择颜色")
//            return
//        }
//        if(mColorSizeCountDatas.size <= 0){
//            ToastUtil.showShortToast("请添加尺码数量")
//            return
//        }

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