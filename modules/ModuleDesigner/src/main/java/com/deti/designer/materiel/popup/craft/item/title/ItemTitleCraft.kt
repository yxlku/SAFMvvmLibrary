package com.deti.designer.materiel.popup.craft.item.title

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemAddCraftTitleBinding
import com.deti.designer.materiel.popup.craft.AddCraftViewModel

class ItemTitleCraft(
    var mViewModel: AddCraftViewModel
): QuickDataBindingItemBinder<ItemTitleCraftEntity, DesignerItemAddCraftTitleBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemAddCraftTitleBinding>,
        data: ItemTitleCraftEntity,
    ) {
        var databinding = holder.dataBinding
        databinding.apply {
            entity = data
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemAddCraftTitleBinding = DesignerItemAddCraftTitleBinding.inflate(layoutInflater, parent, false)
}