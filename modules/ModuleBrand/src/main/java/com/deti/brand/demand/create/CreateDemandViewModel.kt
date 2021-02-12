package com.deti.brand.demand.create

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.brand.demand.create.entity.DemandStyleTypeEntity
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
import com.test.common.ui.item.remark.ItemRemarkEntity
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.deti.brand.main.odm.ODMFragment.Companion.ODM_LIVE_TO_ORDER_LIST
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.DefaultDateFormat
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.format2DateString
import com.test.common.dictionary.dictionaryServiceCorrespondeKeyToValue
import com.test.common.dictionary.dictionaryServiceTypeKeyToValue
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorListEntity
import com.test.common.ui.toast.ToastDrawableEnum
import kotlin.collections.ArrayList


class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {

    /*********************** LiveData - Start****************************/
    /** 快递列表弹窗*/
    val DIALOG_EXPRESS_LIST = SingleLiveEvent<ArrayList<BaseSingleChoiceEntity>>()
    /** 款式分类*/
    val FORM_STYLE_TYPE = SingleLiveEvent<TypesTreeViewEntity>()
    /** 尺码类型*/
    val FORM_SIZE_TYPE = SingleLiveEvent<List<BaseSingleChoiceEntity>>()
    /** 选择颜色*/
    val FORM_COLORS = SingleLiveEvent<DemandColorListEntity>()
    /** 选择尺码数量*/
    val FORM_SIZE_COUNT = SingleLiveEvent<ArrayList<FirstNodeEntity>>()
    /** 时间选择*/
    val FORM_TIMES = SingleLiveEvent<Unit>()

    /** 提交后清空列表数据*/
    val CLEAR_LIST_DATA = SingleLiveEvent<Unit>()
    /** 选择图片布局中的删除*/
    val PIC_CHOOSE = "pic_choose"
    /*********************** LiveData - End****************************/

    /*********************** 其他 - Start****************************/

    /** 图片列表 TODO 还没搞完*/
    var mPicListDatas = arrayListOf<String>("", "", "", "", "")

    /** 尺码类型*/
    /** 全局尺码组数据*/
    var mFindSizeEntityData: CommonFindSizeEntity? = null

    /** 请求数据：快递列表数据*/
    var mNetDataExpressList: DemandExpressListEntity? = null
    /** 请求数据：款式数据*/
    var mNetDataStyleType: DemandStyleTypeEntity? = null


    /*********************** RecyclerView - Start****************************/
    /** 列表实体类集合*/
    var itemListEntitys = arrayListOf<Any>()

    //完善个人信息
    lateinit var itemEntityPersonal : ItemPersonalInfoEntity
    //类型选择
    lateinit var itemEntityTypeChoose: ItemDeamandTypeChooseEntity
    //服务
    lateinit var itemEntityService :ItemServiceEntity

    //图片
    lateinit var itemEntityPic : ItemPicChooseEntity
    //面料信息
    lateinit var itemEntityFabric : ItemUploadFileEntity
    //样衣
    lateinit var itemEntitySamplelothes : ItemExpressEntity
    //制版文件
    lateinit var itemEntityPlate : ItemUploadFileEntity

    /** 款式分类*/
    lateinit var itemEntityFormStyle : ItemFormChooseEntity
    //尺码类型
    lateinit var itemEntityFormSizeType : ItemFormChooseEntity
    //颜色选择
    lateinit var itemEntityFormColor : ItemFormChooseEntity
    //尺码数量
    lateinit var itemEntityFormSizeCount : ItemFormChooseEntity

    /** 单价*/
    lateinit var itemEntityInputPrice : ItemFormInputEntity
    /** 交期*/
    lateinit var itemEntityFormTime : ItemFormChooseEntity
    /** 备注*/
    lateinit var itemEntityInputRemark : ItemRemarkEntity
    /** 下单按钮*/
    lateinit var itemEntityPlaceOrder : ItemPlaceOrderEntity
    /*********************** RecyclerView - End****************************/
    /**
     * 清空所有数据
     */
    fun initInfoEntitys(){
        //完善个人信息
        itemEntityPersonal = ItemPersonalInfoEntity()
        //类型选择
        itemEntityTypeChoose = ItemDeamandTypeChooseEntity()
        //服务
        itemEntityService = ItemServiceEntity()

        //图片
        itemEntityPic = ItemPicChooseEntity()
        //面料信息
        itemEntityFabric = ItemUploadFileEntity(ItemUploadFileEnum.FILE_FABRIC, "请上传面料信息", "(选填)", "上传面料信息")
        //样衣
        itemEntitySamplelothes = ItemExpressEntity()
        //制版文件
        itemEntityPlate = ItemUploadFileEntity(ItemUploadFileEnum.FILE_PLATE, "请上传制版文件", "(选填)", "上传制版文件")

        /** 款式分类*/
        itemEntityFormStyle = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_STYLE,"款式分类", false, "请选择款式分类")
        //尺码类型
        itemEntityFormSizeType = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_TYPE, "尺码类型", false, "请选择所需要的尺码")
        //颜色选择
        itemEntityFormColor = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_COLOR, "颜色选择", false, "可设置多个颜色")
        //尺码数量
        itemEntityFormSizeCount = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_COUNT, "尺码数量", false, "可设置多个")

        /** 单价*/
        itemEntityInputPrice = ItemFormInputEntity("预算单价", false, "请输入价格", unitText = "元")
        /** 交期*/
        itemEntityFormTime = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_TIME, "设置交期", false, "交期最低14天")
        /** 备注*/
        itemEntityInputRemark = ItemRemarkEntity("", "请输入备注（选填）", ObservableField(""), "请输入更多备注信息")
        /** 下单按钮*/
        itemEntityPlaceOrder = ItemPlaceOrderEntity()
    }

    fun initInfoList(){
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
            ItemTransparentLineEntity(),
            itemEntityInputPrice,

            //设置交期
            ItemGrayLineEntity(),
            itemEntityFormTime,

            //备注
            ItemTransparentLineEntity(),
            itemEntityInputRemark,

            //下单按钮
            ItemTransparentLineEntity(),
            itemEntityPlaceOrder.apply {
                contentText.set("确认下单")
            },
            ItemTransparentLineEntity(),
        )
    }

    /**
     * 初始化或刷新UI
     */
    fun initInfoUI(){
        //1、初始化数据
        initInfoEntitys()
        //2、列表
        initInfoList()
        //3、更新Ui
        CLEAR_LIST_DATA.postValue(Unit)
    }
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //初始化列表UI
        initInfoUI()
    }

    /**
     * 订单修改页面初始化
     */
    fun initUpdateDemand(pDemandId: String?){
        pDemandId?.apply {
            //1、修改订单时一定不显示完善个人信息，
            itemEntityPersonal.isShow = false
            //2、修改提交按钮文字为修改
            itemEntityPlaceOrder.contentText.set("确认修改")
            //3、请求要修改需求订单的数据 - 并展示数据
            requestFindDemandIndentInfo(pDemandId)
        }
    }

    /**
     * 获取订单信息 - 只有修改信息页面中才会调用此方法
     */
    fun requestFindDemandIndentInfo(pDemandId: String){
        launchRequest {
            mModel.requestFindDemandIndentInfo(pDemandId).flowDataDeal(
                loadingModel = LoadingModel.LOADING,
                onSuccess = {
                    it?.data?.demandIndent?.apply {
                        //一、模拟数据:
                        // 1、类型选择
                        provideList = arrayListOf("sample", "fabric", "picture", "layout", "production_standard") //样衣、图片
                        // 2、服务
                        serviceType = "bulk"
                        // 3、快递
                        sampleDressExpressId = "123333333"
                        // 4、图片 TODO 统一处理
                        // 5、面料信息
                        fabricInfo = "file://test.apk"
                        // 6、制版文件
                        makeFilePath = "file://假制版文件地址.zip"
                        // 7、款式分类
                        // 8、尺码类型
                        // 9、颜色选择
                        // 10、尺码数量
                        // 11、预算单价
                        unitPrice = "888"
                        // 12、设置交期
                        // 13、备注
                        comment += "：备注"

                        //二、布局控制
                        //1、类型选择
                        itemEntityTypeChoose.mChooseTypes = provideList
                        //2、服务
                        itemEntityService.mServiceProduce.set(BaseSingleChoiceEntity(serviceType, serviceType.dictionaryServiceCorrespondeKeyToValue()))//对应服务
                        itemEntityService.mServiceType.set(BaseSingleChoiceEntity(productionType, productionType.dictionaryServiceTypeKeyToValue()))//服务类型
                        //3、样衣快递
                        itemEntitySamplelothes.apply {
                            mExpressSingleChoiceEntity.set(BaseSingleChoiceEntity(sampleDressExpressType, sampleDressExpressType)) //快递类型
                            mExpressNum.set(sampleDressExpressId) // 快递单号
                        }
                        //4、图片 TODO
                        // 5、面料信息
                        itemEntityFabric.filePath.set(fabricInfo)
                        // 6、制版文件
                        itemEntityPlate.filePath.set(makeFilePath)

                        // 7、款式分类
                        itemEntityFormStyle.apply {
                            //显示内容、弹窗默认选中
                            contentText.set("$genderText - $categoryText - $suitTypeText - $classifyText")
                            //选中的数据
                            mStyleList.apply {
                                add(TypesViewDataBean("", genderText, gender))
                                add(TypesViewDataBean("", categoryText, category))
                                add(TypesViewDataBean("", suitTypeText, suitType))
                                add(TypesViewDataBean("", classifyText, classify))
                            }
                        }
                        // 8、尺码类型
                        // 9、颜色选择
                        // 10、尺码数量

                        // 11、预算单价
                        itemEntityInputPrice.contentText.set(unitPrice)
                        // 12、设置交期
                        itemEntityFormTime.contentText.set(deliveryDate.time.format2DateString(DefaultDateFormat.DATE_YMD))
                        // 13、备注
                        itemEntityInputRemark.contentText.set(comment)


                        //end 刷新列表
                        CLEAR_LIST_DATA.putValue(Unit)
                    }
                },
                onError = {
                    ToastUtil.showShortToast("选中了")
                },
                onFaile = {code: String, msg: String ->
                    LogUtil.d("msg")
                }
            )
        }
    }

    /** 清除信息：尺码类型*/
    fun clearInfoSizeType(){
        itemEntityFormSizeType.apply {
            contentText.set("")
            mSizeTypeData == null       //尺码类型清空
            mDialogPositionSizeTypeData = -1
        }
    }

    /** 清除信息：选择的颜色*/
    fun clearInfoColors(){
        itemEntityFormColor.apply {
            contentText.set("")
            mSelectColorDatas = arrayListOf()  //选中颜色数据
        }
    }
    /** 清除信息：尺码数量*/
    fun clearInfoSizeCount(){
        itemEntityFormSizeCount.apply {
            contentText.set("")
            mColorSizeCountDatas = arrayListOf()
        }
    }


    /**
     * 表单 - 选择
     */
    fun clickFormChoose(view: View, entity: ItemFormChooseEntity){
        when (entity.tag) {
            ItemFormChooseType.CHOOSE_STYLE -> formClickChooseStyle(view, entity) //款式选择
            ItemFormChooseType.CHOOSE_SIZE_TYPE -> formClickChooseSizeType(view, entity) //尺码类型
            ItemFormChooseType.CHOOSE_TIME -> formClickChooseTime(view, entity) //时间选择
            ItemFormChooseType.CHOOSE_COLOR -> formClickChooseColor(view, entity) //选择颜色
            ItemFormChooseType.CHOOSE_SIZE_COUNT -> formClickChooseSizeCount(view, entity) //选择尺码数量
        }
    }

    /**
     * 获取快递列表
     */
    fun clickRequestExpressList(view: View) {
        launchRequest {
            if (mNetDataExpressList == null) {
                mModel.requestExpressList()
                    .flowDataDeal(
                        loadingModel = LoadingModel.NULL,
                        onSuccess = {
                            mNetDataExpressList = it?.data
                            DIALOG_EXPRESS_LIST.putValue(mNetDataExpressList?.dataDictionaryList as ArrayList<BaseSingleChoiceEntity>)
                        }
                    )
            }else{
                DIALOG_EXPRESS_LIST.putValue(mNetDataExpressList?.dataDictionaryList as ArrayList<BaseSingleChoiceEntity>)
            }
        }
    }

    /** 款式类型*/
    fun formClickChooseStyle(view: View, entity: ItemFormChooseEntity) {
        launchRequest {
            if (mNetDataStyleType == null) {
                mModel.requestStyleInfo()
                    .flowDataDeal(
                        loadingModel = LoadingModel.LOADING,
                        onSuccess = {
                            mNetDataStyleType = it?.data
                            FORM_STYLE_TYPE.putValue(mNetDataStyleType as TypesTreeViewEntity)
                        },
                        onFaile = { code: String, msg: String ->
                            LogUtil.d("请求失败$code - $msg")
                        },
                        onError = {
                            LogUtil.exception(ex = it)
                        }
                    )
            }else{
                FORM_STYLE_TYPE.putValue(mNetDataStyleType as TypesTreeViewEntity)
            }
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
                        mFindSizeEntityData = it?.data
                        mFindSizeEntityData?.list?.apply {
                            FORM_SIZE_TYPE.putValue(this as ArrayList<BaseSingleChoiceEntity>)
                        }
                    }
                )
        }
    }

    /** 选择颜色*/
    fun formClickChooseColor(view: View, entity: ItemFormChooseEntity){
        if (mFindSizeEntityData == null || itemEntityFormSizeType.mSizeTypeData == null) {
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
    fun formClickChooseTime(view: View, entity: ItemFormChooseEntity){
        FORM_TIMES.putValue(Unit)
    }



    /**
     * 提交需求
     */
    fun clickPlaceOrder(view: View){
        //提交前的限制 和 提醒
        if(itemEntityTypeChoose.mChooseTypes.size <= 0){
            ToastUtil.showShortToast("请先选择需求类型")
            return
        }
        if(itemEntityService.mServiceType.get() == null || itemEntityService.mServiceType.get()?.id.isNullOrEmpty()){
            ToastUtil.showShortToast("请先选择服务类型")
            return
        }
        if(itemEntityService.mServiceProduce.get() == null || itemEntityService.mServiceProduce.get()?.id.isNullOrEmpty()){
            ToastUtil.showShortToast("请先选择对应服务")
            return
        }
        //TODO 正面图片判断

        if(itemEntityFormStyle.mStyleList.size <= 0){
            ToastUtil.showShortToast("请添加款式分类")
            return
        }
        if (itemEntityFormSizeType.mSizeTypeData == null) {
            ToastUtil.showShortToast("请选择尺码类型")
            return
        }
        if (itemEntityFormColor.mSelectColorDatas.size <= 0) {
            ToastUtil.showShortToast("请选择颜色")
            return
        }
        if(itemEntityFormSizeCount.mColorSizeCountDatas.size <= 0){
            ToastUtil.showShortToast("请添加尺码数量")
            return
        }

        LogUtil.d("钱：${itemEntityInputPrice.contentText.get()}，备注：${itemEntityInputRemark.contentText.get()}")
        if (itemEntityInputPrice.contentText.get()?.isEmpty() == true) {
            ToastUtil.showShortToast("请输入预算单价")
            return
        }
        if (itemEntityFormTime.mTime.isNullOrEmpty()) {
            ToastUtil.showShortToast("请设置交期")
            return
        }

        launchRequest {
            try {
                mModel.requestDemandSubmit(
                    this@CreateDemandViewModel,
                ).flowDataDeal(
                    loadingModel = LoadingModel.LOADING,
                    onSuccess = {
                        //1、提交后清空页面UI数据
                        initInfoUI()
                        //2、提示成功
                        ToastUtil.showShortToast("需求提交成功", toastEnumInterface = ToastDrawableEnum.TOP)
                        //3、跳转到订单列表页面
                        ODM_LIVE_TO_ORDER_LIST.putValue(Unit)
                    }
                )
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }


    }

}