package com.deti.brand.demand.create

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentDemandCreateBinding
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
import com.deti.brand.demand.create.item.service.ItemService
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.loper7.date_time_picker.StringUtils
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.common.ConstantsFun
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.UserInfoEntity
import com.test.common.ui.dialog.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.createDialogSizeCount
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity
import com.test.common.ui.item.remark.ItemRemark
import com.test.common.ui.item.remark.ItemRemarkEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.color.DemandColorDataBean
import com.test.common.ui.popup.color.DemandColorListEntity
import com.test.common.ui.popup.color.dialogChooseColors
import com.test.common.ui.popup.custom.type.createDialogLevelTypes
import com.test.common.ui.popup.dialogBottomSingle
import com.test.common.ui.popup.time.dialogTimeWheel
import com.zlylib.fileselectorlib.utils.DateUtils
import com.zlylib.fileselectorlib.utils.LogUtils
import java.io.Serializable
import java.util.*

/**
 * 创建需求
 */
class CreateDemandFragment(
    /** 要修改的需求单ID*/
    var pDemandId: String? = null,
): BaseFragment<BrandFragmentDemandCreateBinding, CreateDemandViewModel>(
    R.layout.brand_fragment_demand_create,
    BR.viewModel
) {
    /** 弹窗：款式选择*/
    var mPopupViewStyle: BasePopupView? = null
    /** 弹窗：颜色选择*/
    var mPopupViewColor: BasePopupView? = null
    /** 弹窗：颜色对应尺寸数量*/
    var mPopupViewColorSize: BasePopupView? = null

    /** 主页适配器*/
    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()
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
        //修改订单
        mViewModel.initUpdateDemand(pDemandId)
    }
    lateinit var itemTypeChoose: ItemDeamndTypeChoose
    /**
     * 初始化列表
     */
    private fun initRecyclerView() {
        itemTypeChoose = ItemDeamndTypeChoose(activity, mViewModel){
            mBinding.rvContent.scrollToPosition(0)
        }
        mAdapter.apply {
            //灰色线
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            //透明线
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
            //图片选择
            addItemBinder(ItemPicChooseEntity::class.java, ItemPicChoose(activity, mViewModel))
            //类型选择
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, itemTypeChoose)
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
            //完善个人信息
            addItemBinder(ItemPersonalInfoEntity::class.java, ItemPersonalInfoTip(activity))
            //单价
            addItemBinder(ItemFormInputEntity::class.java, ItemFormInput())
            //备注
            addItemBinder(ItemRemarkEntity::class.java, ItemRemark())
            //提交
            addItemBinder(ItemPlaceOrderEntity::class.java, ItemPlaceOrder(mViewModel))
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

////        //TODO 个人信息完善，需要判断显示 -- vm中判断显示
//        addOrRemove(mViewModel.itemEntityPersonal, true)
    }

    override fun initUiChangeLiveData() {
        super.initUiChangeLiveData()
        /** 初始化列表 用处：1、第一次初始化列表 2、提交后清空数据 3、更新选中布局*/
        mViewModel.CLEAR_LIST_DATA.observe(this) {
            //1、类型选择刷新
            mViewModel.itemEntityTypeChoose?.apply { itemTypeChoose.updateUIData(this, this.mChooseTypes) }
            //2、刷新后制定
            mBinding.rvContent.scrollToPosition(0)
            //3、刷新布局
            mAdapter.setList(mViewModel.itemListEntitys)
        }
        /** 样衣 - 快递列表*/
        mViewModel.DIALOG_EXPRESS_LIST.observe(this, {
            activity?.apply {
                it?.dialogBottomSingle(this,
                    "请选择快递",
                    selectedBaseSingleChoiceEntity = mViewModel.itemEntitySamplelothes.mExpressSingleChoiceEntity.get(),
                    callback = { data, position ->
                        //数据赋值
                        mViewModel.itemEntitySamplelothes.mExpressSingleChoiceEntity.set(data)
                    })?.show()
            }
        })
        /** 款式分类*/
        mViewModel.FORM_STYLE_TYPE.observe(this, {
            it?.apply {
                mPopupViewStyle?.apply {
                    show()
                } ?: run{
                    mPopupViewStyle = createDialogLevelTypes(this@CreateDemandFragment.requireActivity(),
                        "请选择款式分类",
                        this,
                        4) { result: ArrayList<TypesViewDataBean?>, resultTextList: ArrayList<String> ->
                        //选择后的数据 - 待提交需求时使用
                        mViewModel.itemEntityFormStyle.apply {
                            //1、数据赋值
                            mStyleList = result
                            //2、选后显示文字
                            contentText.set(resultTextList.joinToString(separator = " - "))
                            //3、清空尺码类型
                            mViewModel.clearInfoSizeType()
                            //4、清空颜色
                            mViewModel.clearInfoColors()
                            mPopupViewColor = null
                            //5、清空尺码数量
                            mViewModel.clearInfoSizeCount()
                            mPopupViewColorSize = null
                        }
                    }.show()
                }
            }
        })
        /** 尺码类型*/
        mViewModel.FORM_SIZE_TYPE.observe(this) {
            activity?.apply {
                it?.dialogBottomSingle(this@apply,
                    "选择尺码类型",
                    mViewModel.itemEntityFormSizeType.mDialogPositionSizeTypeData,
                    callback = { data, position ->
                        //1、选中后的数据赋值
                        mViewModel.itemEntityFormSizeType.apply {
                            mSizeTypeData = mViewModel.mFindSizeEntityData?.list?.get(position)
                            mDialogPositionSizeTypeData = position
                            //1、控制显示到对应item上
                            contentText.set(data.text)
                            //2、清空颜色 和 弹窗
                            mViewModel.clearInfoColors()
                            mPopupViewColor = null
                            //3、清空尺码数量
                            mViewModel.clearInfoSizeCount()
                            mPopupViewColorSize = null
                        }
                    })?.show()
            }
        }
        /** 颜色选择*/
        mViewModel.FORM_COLORS.observe(this) {
            activity?.apply {
                it?.run {
                    mPopupViewColor?.apply {
                        show()
                    } ?: run {
                        mPopupViewColor = dialogChooseColors(this@apply,
                            "选择颜色",
                            this) { selectDatas: ArrayList<DemandColorDataBean>, selectDatasText: ArrayList<String>, basePopupView: BasePopupView ->
                            //1、选中显示的文字
                            mViewModel.itemEntityFormColor.contentText.set(selectDatasText.joinToString(" "))
                            //2、选中的数据
                            mViewModel.itemEntityFormColor.mSelectColorDatas = selectDatas
                            //关闭弹窗
                            basePopupView.dismiss()
                            //3、清空尺码数量
                            mViewModel.clearInfoSizeCount()
                            mPopupViewColorSize = null
                        }.show()
                    }
                } ?: run {
                    ToastUtil.showShortToast("暂无颜色")
                }
            }
        }
        /** 颜色对应尺码选择*/
        mViewModel.FORM_SIZE_COUNT.observe(this) {
            activity?.apply {
                it?.run {
                    mPopupViewColorSize?.apply {
                        this.show()
                    } ?: run {
                        mPopupViewColorSize = createDialogSizeCount(this@apply, "选择尺码和设置数量", this) {
                                adapter: SizeCountAdapter,
                                resultData: ArrayList<CommonColorEntity>,
                                resultText: String,
                                popupView: BasePopupView,
                            ->
                            //1、赋值
                            mViewModel.itemEntityFormSizeCount.mColorSizeCountDatas = resultData
                            //2、控制显示文字
                            mViewModel.itemEntityFormSizeCount.contentText.set(resultText)
                            //3、所有颜色都选了尺寸才会关闭弹窗
                            popupView.dismiss()
                        }.show()
                    }
                }
            }
        }
        /** 时间选择*/
        mViewModel.FORM_TIMES.observe(this) {
                LogUtil.d("sssssssssssssssss")
                activity?.apply {
                    dialogTimeWheel(this,
                        "请选择时间") { millisecond: Long, time: String, popupView: BasePopupView ->
                        var time = StringUtils.conversionTime(millisecond, "yyyy-MM-dd")
                        var day =
                            DateUtils.calculateDifferentDay(System.currentTimeMillis(), millisecond)
                        if (day >= 14) {
                            //1、item显示
                            mViewModel.itemEntityFormTime.contentText.set(time)
                            //2、上传数据赋值
                            mViewModel.itemEntityFormTime.mTime = time
                            popupView.dismiss()
                        } else {
                            ToastUtil.showShortToast("交期最低14天")
                        }
                    }.show()
                }
            }

        /** 图片选择 TODO 未完成 统一整理*/
        LiveDataBus.observe<Triple<ItemPicChooseItemEntity, String, Int>>(this, mViewModel.PIC_CHOOSE, {
                var entity = it.first
                var picFilePath = it.second
                var clickItemPos = it.third

                //请求后的地址
                entity.picPath.set(picFilePath)
                mViewModel.mPicListDatas[clickItemPos] = picFilePath
            }, false)

    }
}