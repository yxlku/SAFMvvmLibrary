package com.deti.basis.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.basis.databinding.BasisItemPerfectFormBinding

class ItemFormPersonalPerfect :QuickDataBindingItemBinder<ItemFormPersonalEntity, BasisItemPerfectFormBinding>(){
    override fun convert(
        holder: BinderDataBindingHolder<BasisItemPerfectFormBinding>,
        data: ItemFormPersonalEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BasisItemPerfectFormBinding = BasisItemPerfectFormBinding.inflate(layoutInflater, parent, false)

}
