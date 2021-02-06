package com.deti.brand.demand.create

import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentDemandCreateBinding
import com.deti.brand.demand.create.item.IItemIsShow
import com.deti.brand.demand.create.item.demandtype.ItemDeamandTypeChooseEntity
import com.deti.brand.demand.create.item.demandtype.ItemDeamndTypeChoose
import com.deti.brand.demand.create.item.express.ItemExpress
import com.deti.brand.demand.create.item.express.ItemExpressEntity
import com.deti.brand.demand.create.item.file.ItemUploadFile
import com.deti.brand.demand.create.item.file.ItemUploadFileEntity
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum.FILE_DESIGN
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum.FILE_FABRIC
import com.deti.brand.demand.create.item.file.ItemUploadFileEnum.FILE_PLATE
import com.deti.brand.demand.create.item.form.*
import com.deti.brand.demand.create.item.grouptitle.ItemGroupTitle
import com.deti.brand.demand.create.item.grouptitle.ItemGroupTitleEntity
import com.deti.brand.demand.create.item.personinfo.ItemPersonalInfoEntity
import com.deti.brand.demand.create.item.personinfo.ItemPersonalInfoTip
import com.deti.brand.demand.create.item.pic.ItemPicChoose
import com.deti.brand.demand.create.item.pic.ItemPicChooseEntity
import com.deti.brand.demand.create.item.pic.ItemPicChooseItemEntity
import com.deti.brand.demand.create.item.placeorder.ItemPlaceOrder
import com.deti.brand.demand.create.item.placeorder.ItemPlaceOrderEntity
import com.deti.brand.demand.create.item.remark.ItemRemark
import com.deti.brand.demand.create.item.remark.ItemRemarkEntity
import com.deti.brand.demand.create.item.service.ItemService
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.loper7.date_time_picker.StringUtils
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import com.test.common.common.ConstantsFun
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonSizeCountEntity
import com.test.common.entity.UserInfoEntity
import com.test.common.ext.chooseFile
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.multiple.createDialogSelectedMultiple
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.dialog.sizecount.createDialogSizeCount
import com.test.common.ui.dialog.tip.createDialogTip
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorListEntity
import com.test.common.ui.popup.color.dialogChooseColors
import com.test.common.ui.popup.dialogBottomSingle
import com.test.common.ui.popup.time.dialogTimeWheel
import com.test.common.ui.popup.type.createDialogLevelTypes
import com.zlylib.fileselectorlib.utils.DateUtils
import java.util.*

/**
 * 创建需求
 */
class CreateDemandFragment : BaseLazyFragment<BrandFragmentDemandCreateBinding, CreateDemandViewModel>(
    R.layout.brand_fragment_demand_create,
    BR.viewModel
) {
    companion object {
        /** 类型选择*/
        const val DIALOG_CHOOSE_TYPE = "dialog_choose_type"
        /** 服务类型弹窗*/
        const val DIALOG_SERVICE_TYPE = "dialog_service_type"
        /** 对应服务弹窗*/
        const val DIALOG_SERVICE_PRODUCE = "dialog_service_produce"
        /** 快递列表弹窗*/
        const val DIALOG_EXPRESS_LIST = "dialog_express_list"
        /** 地址弹窗*/
        const val DIALOG_TIP_ADDRESS = "dialog_tip_address"

        /** 选择图片布局中的删除*/
        const val PIC_CHOOSE = "pic_choose"

        /** 上传文件*/
        const val UPLOAD_FILE = "upload_file"
        /** 款式分类*/
        const val FORM_STYLE_TYPE = "form_style_type"
        /** 尺码类型*/
        const val FORM_SIZE_TYPE = "form_size_type"
        /** 选择尺码数量*/
        const val FORM_SIZE_COUNT = "form_size_count"
        /** 选择颜色*/
        const val FORM_COLORS = "form_colors"
        /** 时间选择*/
        const val FORM_TIME = "form_time"
    }

    /** 主页适配器*/
    var mAdapter = BaseBinderAdapter()

    /** 服务类型弹窗*/
    var mDialogServiceType: BasePopupView? = null

    /** 对应服务弹窗*/
    var mDialogServiceProduce: BasePopupView? = null

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        //TODO testLoginData
        ConstantsFun.User.logoutClearInfo()
        ConstantsFun.User.loginSaveInfo(
            UserInfoEntity(
                "1612008247901",
                "EMP",
                "4d68ee97deef4d6297d167fd2f17b37f"
            )
        )
        //初始化列表
        initRecyclerView()
    }


    /**
     * 初始化列表
     */
    private fun initRecyclerView() {
        mAdapter.apply {
            //灰色线
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            //透明线
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())

            //图片选择
            addItemBinder(ItemPicChooseEntity::class.java, ItemPicChoose(activity, mViewModel))

            //类型选择
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, ItemDeamndTypeChoose(mViewModel))
            //服务
            addItemBinder(ItemServiceEntity::class.java, ItemService(mViewModel))
            //快递
            addItemBinder(ItemExpressEntity::class.java, ItemExpress(mViewModel))
            //上传文件
            addItemBinder(ItemUploadFileEntity::class.java, ItemUploadFile(mViewModel))
            //分组标题
            addItemBinder(ItemGroupTitleEntity::class.java, ItemGroupTitle())
            //选择条目
            addItemBinder(ItemFormChooseEntity::class.java, ItemFormChoose(mViewModel))


            addItemBinder(ItemPersonalInfoEntity::class.java, ItemPersonalInfoTip(activity))


            addItemBinder(ItemFormInputEntity::class.java, ItemFormInput())
            addItemBinder(ItemRemarkEntity::class.java, ItemRemark())
            addItemBinder(ItemPlaceOrderEntity::class.java, ItemPlaceOrder(mViewModel))

        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

    }

    override fun onStart() {
        super.onStart()
        //VM
        //初始化列表
        var listInitItem = arrayListOf(

            //选择需求类型
            itemEntityTypeChoose,

            //透明分割线
            ItemTransparentLineEntity(context),
            //服务
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
            //分割线
            ItemGrayLineEntity(context),
            //款式分类
            ItemFormChooseEntity(ItemFormChooseType.CHOOSE_STYLE,"款式分类", false, "请选择款式分类"),

            //分割线
            ItemGrayLineEntity(context),
            //尺码类型
            itemEntityFormSizeType,

            //分割线
            ItemGrayLineEntity(context),
            //颜色选择
            itemEntityFormColor,
            //分割线
            ItemGrayLineEntity(context),
            //尺码数量
            itemEntityFormSizeCount,

            //透明分割线
            ItemTransparentLineEntity(context),
            ItemFormInputEntity("预算单价", false, "请输入价格", unitText = "元"),

            //分割线
            ItemGrayLineEntity(context),
            //设置交期
            ItemFormChooseEntity(ItemFormChooseType.CHOOSE_TIME, "设置交期", false, "交期最低14天"),

            //透明分割线
            ItemTransparentLineEntity(context),
            //备注
            ItemRemarkEntity(),

            //透明分割线
            ItemTransparentLineEntity(context),
            //下单按钮
            ItemPlaceOrderEntity(),
            ItemTransparentLineEntity(context),
        )
        mAdapter.setList(listInitItem)

        //TODO 个人信息完善，需要判断显示
        addOrRemove(itemEntityPersonal, true, 0)
    }
    //完善个人信息
    var itemEntityPersonal = ItemPersonalInfoEntity()
    //服务
    var itemEntityService = ItemServiceEntity()
    //类型选择
    var itemEntityTypeChoose = ItemDeamandTypeChooseEntity()

    //图片
    var itemEntityPic = ItemPicChooseEntity()
    //面料信息
    var itemEntityFabric = ItemUploadFileEntity(FILE_FABRIC, "请上传面料信息", "(选填)", "上传面料信息")
    //样衣
    var itemEntitySamplelothes = ItemExpressEntity()
    //制版文件
    var itemEntityPlate = ItemUploadFileEntity(FILE_PLATE, "请上传制版文件", "(选填)", "上传制版文件")

    //尺码类型
    var itemEntityFormSizeType = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_TYPE, "尺码类型", false, "请选择所需要的尺码")
    //颜色选择
    var itemEntityFormColor = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_COLOR, "颜色选择", false, "可设置多个颜色")
    //尺码数量
    var itemEntityFormSizeCount = ItemFormChooseEntity(ItemFormChooseType.CHOOSE_SIZE_COUNT, "尺码数量", false, "可设置多个")

    /** 类型选择控制显示隐藏对应的布局*/
    fun controlListFunction(checkEntity: BaseMultipleChoiceEntity){
        var pos = mAdapter.getItemPosition(itemEntityTypeChoose) + 1
        when (checkEntity.id) {
            "picture" -> addOrRemove(itemEntityPic, checkEntity.isSelected, pos)                 //图片
            "fabric" -> addOrRemove(itemEntityFabric, checkEntity.isSelected, pos)                  //面料信息
            "sample" -> addOrRemove(itemEntitySamplelothes, checkEntity.isSelected, pos)                 //样衣
            "production_standard" -> addOrRemove(itemEntityPlate, checkEntity.isSelected, pos)     //制版文件
        }
    }


    /**
     * 添加或删除item
     */
    fun addOrRemove(item: IItemIsShow, isShow: Boolean, pos: Int = 0){
        item.isShow = isShow
        mAdapter.notifyDataSetChanged()
//        if (isShow) {
//            mAdapter.addData(pos, item)
//        }else{
//            mAdapter.remove(item)
//        }
    }
    override fun initUiChangeLiveData() {
        super.initUiChangeLiveData()
        LiveDataBus.observe<Triple<ItemPicChooseItemEntity, String, Int>>(this, PIC_CHOOSE, {
            var entity = it.first
            var picFilePath = it.second
            var clickItemPos = it.third

            //请求后的地址
            entity.picPath.set(picFilePath)
            mViewModel.mPicListDatas[clickItemPos] = picFilePath
        }, false)
        /** 类型选择*/
        LiveDataBus.observe<ItemDeamandTypeChooseEntity>(this, DIALOG_CHOOSE_TYPE, {
                activity?.apply {
                    if(mPopupChooseType == null){
                        mPopupChooseType = arrayListOf(
                            BaseMultipleChoiceEntity("picture","图片", true),
                            BaseMultipleChoiceEntity("fabric", "面料信息", false),
                            BaseMultipleChoiceEntity("sample", "样衣", false),
                            BaseMultipleChoiceEntity("layout", "设计稿", false),
                            BaseMultipleChoiceEntity("production_standard", "制版文件", false),
                        ).createDialogSelectedMultiple(
                            this, "请选择服务类型",
                            callback = { buttonView: CompoundButton?, isChecked: Boolean, checkEntity: BaseMultipleChoiceEntity ->
                                //选中状态更新
                                if (checkEntity.isSelected) {
                                    if (!it.types.contains(checkEntity.text)) {
                                        it.types.add(checkEntity.text)
                                        mViewModel.mChooseTypes.add(checkEntity)
                                        //控制布局
                                        controlListFunction(checkEntity)
                                    }
                                }else{
                                    if (it.types.contains(checkEntity.text)) {
                                        it.types.remove(checkEntity.text)
                                        mViewModel.mChooseTypes.remove(checkEntity)
                                        //控制布局
                                        controlListFunction(checkEntity)
                                    }
                                }

                                var showTextSb = java.lang.StringBuilder()
                                it.types.forEach {
                                    showTextSb.append(it).append(" / ")
                                }
                                it.showText.set(showTextSb.toString())
                            }
                        )
                    }
                    mPopupChooseType?.show()
                }
        }, false)
        /** 选择服务类型*/
        LiveDataBus.observe<ArrayList<BaseSingleChoiceEntity>>(this,
            DIALOG_SERVICE_PRODUCE,
            {
                activity?.apply {
                    if (mDialogServiceType == null) {
                        mDialogServiceType = it.dialogBottomSingle(this, "请选择服务类型", callback = { data, position->
                            mViewModel.mServiceProduce.set(data)
                        })
                    }
                    mDialogServiceType?.show()
                }
            },
            false)
        /** 对应服务*/
        LiveDataBus.observe<ArrayList<BaseSingleChoiceEntity>>(this,
            DIALOG_SERVICE_TYPE,
            {
                activity?.apply {
                    if (mDialogServiceProduce == null) {
                        mDialogServiceProduce = it.dialogBottomSingle(this, "请选择对应服务", callback = { data, position->
                            mViewModel.mServiceType.set(data)
                        })
                    }
                    mDialogServiceProduce?.show()
                }
            },
            false)
        /** 地址提示弹窗*/
        LiveDataBus.observe<Pair<View, String>>(this, DIALOG_TIP_ADDRESS, {
            activity?.apply {
                it.second.createDialogTip(this, it.first).show()
            }
        }, false)

        /** 样衣 - 快递列表*/
        LiveDataBus.observe<Pair<ArrayList<BaseSingleChoiceEntity>, Int>>(this,
            DIALOG_EXPRESS_LIST,
            {
                activity?.apply {
                    it.first.dialogBottomSingle(this, "请选择快递", it.second, callback = { data, position->
                        mViewModel.mExpressSingleChoiceEntity.set(data)
                    }).show()
                }
            },
            false)
        /** 上传文件*/
        LiveDataBus.observe<ItemUploadFileEntity>(this, UPLOAD_FILE, {
            chooseFile(activity as AppCompatActivity?){ filePath ->
                it.filePath.set(filePath)
                when (it.tag) {
                    //TODO 这里选择后应该是请求接口
                    FILE_FABRIC -> { //面料信息
                        mViewModel.mFilePathFabric  = filePath
                    }
                    FILE_PLATE -> { //制版文件
                        mViewModel.mFilePathPlate  = filePath
                    }
                    FILE_DESIGN -> { //设计稿
                        mViewModel.mFilePathDesign = filePath
                    }
                }
            }
        }, false)
        /** 款式分类*/
        LiveDataBus.observe<Pair<TypesTreeViewEntity, ItemFormChooseEntity>>(this, FORM_STYLE_TYPE, {
            activity?.apply {
                if (mPopupStyle == null) {
                    mPopupStyle = createDialogLevelTypes(this, "请选择款式分类", it.first, 4) { datas ->
                        var sb: StringBuilder = StringBuilder()
                        //选择后的数据 - 待提交需求时使用
                        mViewModel.mStyleList = datas
                        //拼接展示使用
                        for(i in 0 until  datas.size){
                            var bean = datas[i]
                            sb.append(bean?.text)
                            if (i != datas.size - 1) {
                                sb.append(" - ")
                            }
                        }
                        it.second.contentText.set(sb.toString())
                        //1、清空尺码类型
                        clearInfoSizeType()
                        //2、清空颜色
                        clearInfoColors()
                        //3、清空尺码数量
                        clearInfoSizeCount()
                    }
                }
                mPopupStyle?.show()
            }
        }, false)
        /** 尺码类型*/
        LiveDataBus.observe<Pair<List<BaseSingleChoiceEntity>, ItemFormChooseEntity>>(this, FORM_SIZE_TYPE, {
            activity?.apply {
//                if(mPopupSizeType == null) {
                    mPopupSizeType = it.first.dialogBottomSingle(this, "选择尺码类型", callback = { data, position->
                        mViewModel.mSizeTypeData = mViewModel.cFindSizeEntityData?.list?.get(position)
                        it.second.contentText.set(data.text)
                        //1、清空颜色
                        clearInfoColors()
                        //2、清空尺码数量
                        clearInfoSizeCount()
                    })
//                }
                mPopupSizeType?.show()
            }
        }, false)
        /** 选择颜色*/
        LiveDataBus.observe<Pair<ItemFormChooseEntity, DemandColorListEntity>>(this, FORM_COLORS, {
            activity?.apply {
                if (mPopupColor == null) {
                    mPopupColor = dialogChooseColors(this, "选择颜色", it.second) { datas ->
                        var sb = StringBuilder()
                        datas.forEach {
                            sb.append(it.name).append(" ")
                        }
                        it.first.contentText.set(sb.toString())
                        mViewModel.mSelectColorDatas = datas
                        //1、清空尺码数量
                        clearInfoSizeCount()
                    }
                }
                mPopupColor?.show()
            }
        }, false)
        /** 尺码数量选择*/
        LiveDataBus.observe<Pair<ArrayList<FirstNodeEntity>, ItemFormChooseEntity>>(this, FORM_SIZE_COUNT, {
              activity?.apply {
                  if (mPopupColorSizeCount == null) {
                      mPopupColorSizeCount = createDialogSizeCount(this, "选择尺码和设置数量", it.first){datas: List<BaseNode>, popupView: BasePopupView ->
                          var sb = StringBuilder()
                          mViewModel.mColorSizeCountDatas.clear()
                          datas.forEach {
                              //1、点击的时候颜色数量不能为空
                              if(it is FirstNodeEntity && it.count <= 0){
                                  ToastUtil.showShortToast("${it.color} 未选择数量")
                                  return@createDialogSizeCount
                              }
                              //尺码对应数量
                              var sizeCountList = arrayListOf<CommonSizeCountEntity>()
                              //2、所有颜色都不为空的时候，拼接所有已选择的数据，展示到布局上
                              it.childNode?.forEach {
                                  var secondEntity = it as SecondNodeEntity
                                  if(secondEntity.count > 0) {
                                      sizeCountList.add(
                                          CommonSizeCountEntity(secondEntity.size, secondEntity.count)
                                      )
                                      sb.append("【")
                                          .append(secondEntity.color)
                                          .append(": ")
                                          .append(secondEntity.size)
                                          .append(": ")
                                          .append(secondEntity.count)
                                          .append("】 ")
                                  }
                              }
                              //提交数据数据拼接
                              mViewModel.mSizeId = mViewModel.mSizeTypeData?.id
                              if (it is FirstNodeEntity) {
                                  var colorEntity = CommonColorEntity(
                                      mViewModel.mSizeTypeData?.id,
                                      it.color,
                                      it.colorCode,
                                      sizeCountList
                                  )
                                  mViewModel.mColorSizeCountDatas.add(colorEntity)

                                  LogUtil.d("colorsSizes: ${JsonUtil.toJson(mViewModel.mColorSizeCountDatas)}")
                              }
                          }
                          it.second.contentText.set(sb.toString())
                          //3、所有颜色都选了尺寸才会关闭弹窗
                          popupView.dismiss()
                      }
                  }
                  mPopupColorSizeCount?.show()
              }
        }, false)
        /** 时间选择*/
        LiveDataBus.observe<ItemFormChooseEntity>(this, FORM_TIME, {
            if (mPopupTime == null) {
                activity?.apply {
                    mPopupTime = dialogTimeWheel(this,"请选择时间"){millisecond: Long, time: String , popupView: BasePopupView ->
                        var time = StringUtils.conversionTime(millisecond, "yyyy-MM-dd")
                        var day = DateUtils.calculateDifferentDay(System.currentTimeMillis(), millisecond)
                        if(day >= 14) {
                            it.contentText.set(time)
                            mViewModel.mTime = time
                            popupView.dismiss()
                        }else{
                            ToastUtil.showShortToast("交期最低14天")
                        }
                    }
                }
            }
            mPopupTime?.show()
        }, false)
    }
    /** 弹窗： 类型选择*/
    var mPopupChooseType: BasePopupView? = null
    /** 弹窗：款式分类*/
    var mPopupStyle: BasePopupView? = null
    /** 弹窗：尺码类型*/
    var mPopupSizeType: BasePopupView? = null
    /** 弹窗：时间*/
    var mPopupTime: BasePopupView? = null
    /** 弹窗：颜色*/
    var mPopupColor: BasePopupView? = null
    /** 弹窗：尺寸数量*/
    var mPopupColorSizeCount: BasePopupView? = null


    /** 清除信息：尺码类型*/
    fun clearInfoSizeType(){
        mViewModel.mSizeTypeData = null
        mPopupSizeType = null
        itemEntityFormSizeType.contentText.set("")
    }

    /** 清除信息：选择的颜色*/
    fun clearInfoColors(){
        mViewModel.mSelectColorDatas = arrayListOf()
        mPopupColor = null
        itemEntityFormColor.contentText.set("")
    }
    /** 清除信息：尺码数量*/
    fun clearInfoSizeCount(){
        //尺码数量
        mPopupColorSizeCount = null
        itemEntityFormSizeCount.contentText.set("")
    }
}