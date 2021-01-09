package com.deti.brand.main.odm.demand.create.item.service

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding

class ItemService: QuickDataBindingItemBinder<ItemServiceEntity, BrandItemDemandServiceBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandServiceBinding>,
        data: ItemServiceEntity,
    ) {
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandServiceBinding = BrandItemDemandServiceBinding.inflate(layoutInflater, parent, false)
}