package com.deti.brand.demand.create

import android.view.View
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
import com.safmvvm.bus.SingleLiveEvent
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
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.dialog.sizecount.createDialogSizeCount
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorListEntity
import com.test.common.ui.popup.color.dialogChooseColors
import com.test.common.ui.popup.custom.tip.createDialogTitleTip
import com.test.common.ui.popup.custom.tip.createDialogTitleTipBottom
import com.test.common.ui.popup.custom.type.createDialogLevelTypes
import com.test.common.ui.popup.dialogBottomSingle
import com.test.common.ui.popup.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.popup.multiple.adapter.MultipleChoiceAdapter
import com.test.common.ui.popup.multiple.createDialogSelectedMultiple
import com.test.common.ui.popup.time.dialogTimeWheel
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
        val DIALOG_CHOOSE_TYPE = SingleLiveEvent<ItemDeamandTypeChooseEntity>()

        /** 快递列表弹窗*/
        val DIALOG_EXPRESS_LIST = SingleLiveEvent<Pair<ArrayList<BaseSingleChoiceEntity>, Int>>()
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
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, ItemDeamndTypeChoose(activity, mViewModel))
            //服务
            addItemBinder(ItemServiceEntity::class.java, ItemService(activity, mViewModel))
            //快递
            addItemBinder(ItemExpressEntity::class.java, ItemExpress(activity, mViewModel))
            //上传文件
            addItemBinder(ItemUploadFileEntity::class.java, ItemUploadFile(activity as AppCompatActivity?, mViewModel))
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
        mAdapter.setList(mViewModel.itemListEntitys)

////        //TODO 个人信息完善，需要判断显示 -- vm中判断显示
//        addOrRemove(mViewModel.itemEntityPersonal, true)
    }

    override fun initUiChangeLiveData() {
        super.initUiChangeLiveData()
        /** 样衣 - 快递列表*/
        DIALOG_EXPRESS_LIST.observe(this, {
            activity?.apply {
                it?.first?.dialogBottomSingle(this, "请选择快递", it?.second, callback = { data, position->
                    mViewModel.mExpressSingleChoiceEntity.set(data)
                })?.show()
            }
        })





        LiveDataBus.observe<Triple<ItemPicChooseItemEntity, String, Int>>(this, PIC_CHOOSE, {
            var entity = it.first
            var picFilePath = it.second
            var clickItemPos = it.third

            //请求后的地址
            entity.picPath.set(picFilePath)
            mViewModel.mPicListDatas[clickItemPos] = picFilePath
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
//    /** 弹窗： 类型选择*/
//    var mPopupChooseType: BasePopupView? = null
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
//        mViewModel.mSizeTypeData = null
//        mPopupSizeType = null
//        itemEntityFormSizeType.contentText.set("")
    }

    /** 清除信息：选择的颜色*/
    fun clearInfoColors(){
//        mViewModel.mSelectColorDatas = arrayListOf()
//        mPopupColor = null
//        itemEntityFormColor.contentText.set("")
    }
    /** 清除信息：尺码数量*/
    fun clearInfoSizeCount(){
//        //尺码数量
//        mPopupColorSizeCount = null
//        itemEntityFormSizeCount.contentText.set("")
    }
}