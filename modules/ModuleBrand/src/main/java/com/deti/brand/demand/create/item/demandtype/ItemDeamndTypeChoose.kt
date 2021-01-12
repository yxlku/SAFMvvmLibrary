package com.deti.brand.demand.create.item.demandtype

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandTypeChooseBinding

class ItemDeamndTypeChoose(
    var mActivity: Activity?
): QuickDataBindingItemBinder<ItemDeamandTypeChooseEntity, BrandItemDemandTypeChooseBinding>() {

    var mViewModel = ItemDeamandTypeViewModel(mActivity)

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandTypeChooseBinding>,
        data: ItemDeamandTypeChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewModel = mViewModel.apply {
            mAdapter = adapter
        }
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandTypeChooseBinding  = BrandItemDemandTypeChooseBinding.inflate(layoutInflater, parent, false)
}