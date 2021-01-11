package com.deti.brand.demand.create.item.placeorder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPlaceOrderBinding

class ItemPlaceOrder: QuickDataBindingItemBinder<ItemPlaceOrderEntity, BrandItemPlaceOrderBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPlaceOrderBinding>,
        data: ItemPlaceOrderEntity,
    ) {
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPlaceOrderBinding = BrandItemPlaceOrderBinding.inflate(layoutInflater, parent, false)
}