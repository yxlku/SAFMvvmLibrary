package com.deti.brand.main.odm.demand.create.item.grouptitle

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemGroupTitleBinding

class ItemGroupTitle: QuickDataBindingItemBinder<ItemGroupTitleEntity, BrandItemGroupTitleBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemGroupTitleBinding>,
        data: ItemGroupTitleEntity,
    ) {
        var binding : BrandItemGroupTitleBinding = holder.dataBinding
        binding.entity = data
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemGroupTitleBinding = BrandItemGroupTitleBinding.inflate(layoutInflater, parent, false)
}