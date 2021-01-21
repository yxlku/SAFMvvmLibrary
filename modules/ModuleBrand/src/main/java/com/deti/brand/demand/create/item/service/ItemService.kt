package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding
import com.deti.brand.demand.create.CreateDemandViewModel

/**
 * 服务
 */
class ItemService(
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemServiceEntity, BrandItemDemandServiceBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandServiceBinding>,
        data: ItemServiceEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandServiceBinding = BrandItemDemandServiceBinding.inflate(layoutInflater, parent, false)
}