package com.deti.basis.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.basis.databinding.BasisFragmentAddAddressBinding
import com.deti.basis.databinding.BasisItemPersonalDetiAddressBinding

class ItemPersonalDetiAddress : QuickDataBindingItemBinder<ItemPersonalDetiAddressEntity, BasisItemPersonalDetiAddressBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BasisItemPersonalDetiAddressBinding>,
        data: ItemPersonalDetiAddressEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BasisItemPersonalDetiAddressBinding = BasisItemPersonalDetiAddressBinding.inflate(layoutInflater, parent, false)
}