package com.deti.basis.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.basis.databinding.BasisItemPerfectTitleBinding

class ItemTitlePersonalPerfect: QuickDataBindingItemBinder<ItemTitlePersonalEntity, BasisItemPerfectTitleBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BasisItemPerfectTitleBinding>,
        data: ItemTitlePersonalEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BasisItemPerfectTitleBinding = BasisItemPerfectTitleBinding.inflate(layoutInflater, parent, false)
}