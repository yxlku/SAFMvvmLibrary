package com.deti.designer.style.popup.item.other

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.databinding.DesignerItemStyleEditOtherBinding

class ItemOther: QuickDataBindingItemBinder<ItemOtherEntity, DesignerItemStyleEditOtherBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemStyleEditOtherBinding>,
        data: ItemOtherEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemStyleEditOtherBinding = DesignerItemStyleEditOtherBinding.inflate(layoutInflater, parent, false)
}