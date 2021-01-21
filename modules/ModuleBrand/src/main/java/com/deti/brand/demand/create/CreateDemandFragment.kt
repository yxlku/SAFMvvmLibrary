package com.deti.brand.demand.create

import android.view.View
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
import com.deti.brand.demand.create.item.form.ItemFormChoose
import com.deti.brand.demand.create.item.form.ItemFormChooseEntity
import com.deti.brand.demand.create.item.form.ItemFormInput
import com.deti.brand.demand.create.item.form.ItemFormInputEntity
import com.deti.brand.demand.create.item.grouptitle.ItemGroupTitle
import com.deti.brand.demand.create.item.grouptitle.ItemGroupTitleEntity
import com.deti.brand.demand.create.item.personinfo.ItemPersonalInfoEntity
import com.deti.brand.demand.create.item.personinfo.ItemPersonalInfoTip
import com.deti.brand.demand.create.item.pic.ItemPicChoose
import com.deti.brand.demand.create.item.pic.ItemPicChooseEntity
import com.deti.brand.demand.create.item.placeorder.ItemPlaceOrder
import com.deti.brand.demand.create.item.placeorder.ItemPlaceOrderEntity
import com.deti.brand.demand.create.item.remark.ItemRemark
import com.deti.brand.demand.create.item.remark.ItemRemarkEntity
import com.deti.brand.demand.create.item.service.ItemService
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseFragment
import com.test.common.common.ConstantsFun
import com.test.common.common.entity.UserInfoEntity
import com.test.common.ui.dialog.tip.createDialogTip
import com.test.common.ui.line.ItemGrayLine
import com.test.common.ui.line.ItemGrayLineEntity
import com.test.common.ui.line.ItemTransparentLine
import com.test.common.ui.line.ItemTransparentLineEntity
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.dialogBottomSingle

/**
 * 创建需求
 */
class CreateDemandFragment : BaseFragment<BrandFragmentDemandCreateBinding, CreateDemandViewModel>(
    R.layout.brand_fragment_demand_create,
    BR.viewModel
) {
    companion object {
        /** 服务类型弹窗*/
        const val DIALOG_SERVICE_TYPE = "dialog_service_type"
        /** 对应服务弹窗*/
        const val DIALOG_SERVICE_PRODUCE = "dialog_service_produce"
        /** 快递列表弹窗*/
        const val DIALOG_EXPRESS_LIST = "dialog_express_list"
        /** 地址弹窗*/
        const val DIALOG_TIP_ADDRESS = "dialog_tip_address"
    }

    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()
        //TODO testLoginData
        ConstantsFun.User.logoutClearInfo()
        ConstantsFun.User.loginSaveInfo(
            UserInfoEntity(
                "1611813195743",
                "EMP",
                "1d70741e554043a48285b311e1064753"
            )
        )
        //初始化列表
        initRecyclerView()
    }

    var mDialogServiceType: BasePopupView? = null
    var mDialogServiceProduce: BasePopupView? = null
    override fun initUiChangeLiveData() {
        super.initUiChangeLiveData()
        /** 通用单选弹窗*/
        LiveDataBus.observe<ArrayList<BaseSingleChoiceEntity>>(this,
            DIALOG_SERVICE_TYPE,
            {
                activity?.apply {
                    if (mDialogServiceType == null) {
                        mDialogServiceType = it.dialogBottomSingle(this, "请选择服务类型", callback = {
                            mViewModel.mServiceType.set(it.text)
                        })
                    }
                    mDialogServiceType?.show()
                }
            },
            false)
        /** 对应服务*/
        LiveDataBus.observe<ArrayList<BaseSingleChoiceEntity>>(this,
            DIALOG_SERVICE_PRODUCE,
            {
                activity?.apply {
                    if (mDialogServiceProduce == null) {
                        mDialogServiceProduce = it.dialogBottomSingle(this, "请选择对应服务", callback = {
                            mViewModel.mServiceProduce.set(it.text)
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

        /** 快递列表*/
        LiveDataBus.observe<Pair<ArrayList<BaseSingleChoiceEntity>, Int>>(this,
            DIALOG_EXPRESS_LIST,
            {
                activity?.apply {
                    it.first.dialogBottomSingle(this, "请选择快递", it.second, callback = {
                        mViewModel.mExpressSingleChoiceEntity.set(it)
                    }).show()
                }
            },
            false)
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
            //服务
            addItemBinder(ItemServiceEntity::class.java, ItemService(mViewModel))
            //快递
            addItemBinder(ItemExpressEntity::class.java, ItemExpress(mViewModel))


            addItemBinder(ItemPersonalInfoEntity::class.java, ItemPersonalInfoTip(activity))
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, ItemDeamndTypeChoose(activity))
            addItemBinder(ItemPicChooseEntity::class.java, ItemPicChoose(activity))
            addItemBinder(ItemUploadFileEntity::class.java, ItemUploadFile(activity as AppCompatActivity?))
            addItemBinder(ItemGroupTitleEntity::class.java, ItemGroupTitle())

            addItemBinder(ItemFormChooseEntity::class.java, ItemFormChoose(activity))

            addItemBinder(ItemFormInputEntity::class.java, ItemFormInput())
            addItemBinder(ItemRemarkEntity::class.java, ItemRemark())
            addItemBinder(ItemPlaceOrderEntity::class.java, ItemPlaceOrder())

        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        //VM
        var listData = arrayListOf(
            //提示完善个人信息
            ItemPersonalInfoEntity(),

            //选择需求类型
            ItemDeamandTypeChooseEntity(),

            //透明分割线
            ItemTransparentLineEntity(context),
            //服务
            ItemServiceEntity(),

            //透明分割线
            ItemTransparentLineEntity(context),
            //快递
            ItemExpressEntity(),

            //图片
            ItemPicChooseEntity(),

            //透明分割线
            ItemTransparentLineEntity(context),
            //上传面料信息
            ItemUploadFileEntity(),

            //透明分割线
            ItemTransparentLineEntity(context),
            //上传制版信息
            ItemUploadFileEntity(),

            //分组标题 //请填写服务详细信息
            ItemGroupTitleEntity("请填写服务详细信息"),
            //分割线
            ItemGrayLineEntity(context),
            //款式分类
            ItemFormChooseEntity("款式分类", false, "请选择款式分类"),

            //分割线
            ItemGrayLineEntity(context),
            //尺码类型
            ItemFormChooseEntity("尺码类型", false, "请选择所需要的尺码"),

            //分割线
            ItemGrayLineEntity(context),
            //颜色选择
            ItemFormChooseEntity("颜色选择", false, "可设置多个颜色"),

            //分割线
            ItemGrayLineEntity(context),
            //颜色选择
            ItemFormChooseEntity("尺码数量", false, "可设置多个"),

            //透明分割线
            ItemTransparentLineEntity(context),
            ItemFormInputEntity("预算单价", true, "请输入价格", unitText = "元"),

            //分割线
            ItemGrayLineEntity(context),
            //设置交期
            ItemFormChooseEntity("设置交期", false, "交期最低14天"),

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
        mAdapter.setList(listData)
    }
}