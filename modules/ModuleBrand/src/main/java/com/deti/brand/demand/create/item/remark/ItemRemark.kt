package com.deti.brand.demand.create.item.remark

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemRemarkBinding

class ItemRemark: QuickDataBindingItemBinder<ItemRemarkEntity, BrandItemRemarkBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemRemarkBinding>,
        data: ItemRemarkEntity,
    ) {

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemRemarkBinding = BrandItemRemarkBinding.inflate(layoutInflater, parent, false)
}