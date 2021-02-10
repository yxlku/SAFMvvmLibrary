package com.deti.designer.style.popup.item.input

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemStyleEditInputBinding

/**
 * 输入
 */
class ItemInput: QuickDataBindingItemBinder<ItemInputEntity, DesignerItemStyleEditInputBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemStyleEditInputBinding>,
        data: ItemInputEntity,
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
    ): DesignerItemStyleEditInputBinding = DesignerItemStyleEditInputBinding.inflate(layoutInflater, parent, false)
}