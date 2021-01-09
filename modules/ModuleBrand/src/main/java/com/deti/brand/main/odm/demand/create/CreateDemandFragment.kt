package com.deti.brand.main.odm.demand.create

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentDemandCreateBinding
import com.deti.brand.main.odm.demand.create.item.demandtype.ItemDeamandTypeChooseEntity
import com.deti.brand.main.odm.demand.create.item.demandtype.ItemDeamndTypeChoose
import com.deti.brand.main.odm.demand.create.item.personinfo.ItemPersonalInfoEntity
import com.deti.brand.main.odm.demand.create.item.personinfo.ItemPersonalInfoTip
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
            //请填写服务详细信息
        )


        mAdapter.apply {
            addItemBinder(ItemPersonalInfoEntity::class.java, ItemPersonalInfoTip())
            addItemBinder(ItemDeamandTypeChooseEntity::class.java, ItemDeamndTypeChoose())
            addItemBinder(ItemServiceEntity::class.java, ItemService())
            setList(listData)
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }


    }
}