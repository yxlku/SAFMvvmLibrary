package com.deti.brand.demand.create.item.form

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemFormChooseBinding

class ItemFormChoose: QuickDataBindingItemBinder<ItemFormChooseEntity, BrandItemFormChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemFormChooseBinding>,
        data: ItemFormChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemFormChooseBinding = BrandItemFormChooseBinding.inflate(layoutInflater, parent, false)
}