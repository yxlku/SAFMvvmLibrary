package com.deti.brand.demand.create.item.express

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandExpressBinding

class ItemExpress: QuickDataBindingItemBinder<ItemExpressEntity, BrandItemDemandExpressBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandExpressBinding>,
        data: ItemExpressEntity,
    ) {

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandExpressBinding = BrandItemDemandExpressBinding.inflate(layoutInflater, parent, false)
}