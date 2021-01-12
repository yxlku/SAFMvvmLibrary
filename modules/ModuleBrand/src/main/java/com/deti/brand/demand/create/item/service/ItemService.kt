package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding

class ItemService(
    var mActivity: Activity?
): QuickDataBindingItemBinder<ItemServiceEntity, BrandItemDemandServiceBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandServiceBinding>,
        data: ItemServiceEntity,
    ) {
        var mViewModel = ItemServiceViewModel(mActivity, adapter)
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewModel = mViewModel
        binding.executePendingBindings()

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandServiceBinding = BrandItemDemandServiceBinding.inflate(layoutInflater, parent, false)
}