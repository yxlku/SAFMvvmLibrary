package com.deti.designer.materiel.popup.craft.item.input

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemAddCraftInputBinding
import com.deti.designer.materiel.popup.craft.AddCraftViewModel

/**
 * 输入类型的
 */
class ItemInputCraft(
    var mViewModel: AddCraftViewModel
): QuickDataBindingItemBinder<ItemInputCraftEntity, DesignerItemAddCraftInputBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemAddCraftInputBinding>,
        data: ItemInputCraftEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemAddCraftInputBinding = DesignerItemAddCraftInputBinding.inflate(layoutInflater, parent, false)

}