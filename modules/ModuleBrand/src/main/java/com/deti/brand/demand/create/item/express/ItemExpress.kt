package com.deti.brand.demand.create.item.express

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandExpressBinding
import com.deti.brand.demand.create.item.service.ItemServiceViewModel

class ItemExpress(
    var mActivity: Activity?
): QuickDataBindingItemBinder<ItemExpressEntity, BrandItemDemandExpressBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandExpressBinding>,
        data: ItemExpressEntity,
    ) {
        var mViewModel = ItemExpressViewModel(mActivity, adapter)
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewModel = mViewModel
        binding.executePendingBindings()

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandExpressBinding = BrandItemDemandExpressBinding.inflate(layoutInflater, parent, false)
}