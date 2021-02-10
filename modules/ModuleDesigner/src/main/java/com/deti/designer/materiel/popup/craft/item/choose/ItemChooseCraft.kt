package com.deti.designer.materiel.popup.craft.item.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemAddCraftChooseBinding
import com.deti.designer.materiel.popup.craft.AddCraftViewModel

/**
 * 选择
 */
class ItemChooseCraft(
    var mViewModel: AddCraftViewModel
): QuickDataBindingItemBinder<ItemChooseCraftEntity, DesignerItemAddCraftChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemAddCraftChooseBinding>,
        data: ItemChooseCraftEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemAddCraftChooseBinding = DesignerItemAddCraftChooseBinding.inflate(layoutInflater, parent, false)

}