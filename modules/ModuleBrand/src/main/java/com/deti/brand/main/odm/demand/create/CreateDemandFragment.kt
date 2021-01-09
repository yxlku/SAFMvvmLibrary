package com.deti.brand.main.odm.demand.create

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentDemandCreateBinding
import com.deti.brand.main.odm.demand.create.item.demandtype.ItemDeamandTypeChooseEntity
import com.deti.brand.main.odm.demand.create.item.demandtype.ItemDeamndTypeChoose
import com.deti.brand.main.odm.demand.create.item.file.ItemUploadFile
import com.deti.brand.main.odm.demand.create.item.file.ItemUploadFileEntity
import com.deti.brand.main.odm.demand.create.item.form.ItemFormChoose
import com.deti.brand.main.odm.demand.create.item.form.ItemFormChooseEntity
import com.deti.brand.main.odm.demand.create.item.form.ItemFormInput
import com.deti.brand.main.odm.demand.create.item.form.ItemFormInputEntity
import com.deti.brand.main.odm.demand.create.item.grouptitle.ItemGroupTitle
import com.deti.brand.main.odm.demand.create.item.grouptitle.ItemGroupTitleEntity
import com.deti.brand.main.odm.demand.create.item.line.ItemGrayLine
import com.deti.brand.main.odm.demand.create.item.line.ItemGrayLineEntity
import com.deti.brand.main.odm.demand.create.item.line.ItemTransparentLine
import com.deti.brand.main.odm.demand.create.item.line.ItemTransparentLineEntity
import com.deti.brand.main.odm.demand.create.item.personinfo.ItemPersonalInfoEntity
import com.deti.brand.main.odm.demand.create.item.personinfo.ItemPersonalInfoTip
import com.deti.brand.main.odm.demand.create.item.pic.ItemPicChoose
import com.deti.brand.main.odm.demand.create.item.pic.ItemPicChooseEntity
import com.deti.brand.main.odm.demand.create.item.placeorder.ItemPlaceOrder
import com.deti.brand.main.odm.demand.create.item.placeorder.ItemPlaceOrderEntity
import com.deti.brand.main.odm.demand.create.item.remark.ItemRemark
import com.deti.brand.main.odm.demand.create.item.remark.ItemRemarkEntity
import com.deti.brand.main.odm.demand.create.item.service.ItemService
import com.deti.brand.main.odm.demand.create.item.service.ItemServiceEntity
import com.safmvvm.mvvm.view.BaseFragment

/**
 * 创建需求
 */
class CreateDemandFragment : BaseFragment<BrandFragmentDemandCreateBinding, CreateDemandViewModel>(
    R.layout.brand_fragment_demand_create,
    BR.viewModel
) {
    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()

        //初始化列表
        initRecyclerView()
    }

    /**
     * 初始化列表
     */
    private fun initRecyclerView() {
        //VM
        var listData = arrayListOf(
            //提示完善个人信息
            ItemPersonalInfoEntity(),
            //选择需求类型
            ItemDeamandTypeChooseEntity(),
            //服务
            ItemServiceEntity(),
            //图片
            ItemPicChooseEntity(),
            //上传面料信息
            ItemUploadFileEntity(),
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
            //款式分类
            ItemFormChooseEntity("尺码类型", false, "请选择所需要的尺码"),
            //分割线
            ItemGrayLineEntity(context),
            //款式分类
            ItemFormChooseEntity("颜色选择", false, "可设置多个颜色"),
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


        mAdapter.apply {
            addItemBinder(ItemPersonalInfoEntity::class.java, ItemPersonalInfoTip())
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, ItemDeamndTypeChoose())
            addItemBinder(ItemServiceEntity::class.java, ItemService())
            addItemBinder(ItemPicChooseEntity::class.java, ItemPicChoose())
            addItemBinder(ItemUploadFileEntity::class.java, ItemUploadFile())
            addItemBinder(ItemGroupTitleEntity::class.java, ItemGroupTitle())
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            addItemBinder(ItemFormChooseEntity::class.java, ItemFormChoose())
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
            addItemBinder(ItemFormInputEntity::class.java, ItemFormInput())
            addItemBinder(ItemRemarkEntity::class.java, ItemRemark())
            addItemBinder(ItemPlaceOrderEntity::class.java, ItemPlaceOrder())
            setList(listData)
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }


    }
}