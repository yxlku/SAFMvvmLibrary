package com.deti.designer.style.popup.item.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemStyleEditChooseBinding

/**
 * 选择
 */
class ItemChoose: QuickDataBindingItemBinder<ItemChooseEntity, DesignerItemStyleEditChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemStyleEditChooseBinding>,
        data: ItemChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemStyleEditChooseBinding = DesignerItemStyleEditChooseBinding.inflate(layoutInflater, parent, false)
}