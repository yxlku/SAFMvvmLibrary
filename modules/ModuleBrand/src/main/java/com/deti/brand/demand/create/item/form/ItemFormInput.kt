package com.deti.brand.demand.create.item.form

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemFormInputBinding

class ItemFormInput: QuickDataBindingItemBinder<ItemFormInputEntity, BrandItemFormInputBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemFormInputBinding>,
        data: ItemFormInputEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemFormInputBinding = BrandItemFormInputBinding.inflate(layoutInflater, parent, false)
}