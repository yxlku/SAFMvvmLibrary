package com.deti.designer.materiel.popup.detaile.item.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemMaterielDetaileChooseBinding

class ItemChoose: QuickDataBindingItemBinder<ItemChooseEntity, DesignerItemMaterielDetaileChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemMaterielDetaileChooseBinding>,
        data: ItemChooseEntity,
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
    ): DesignerItemMaterielDetaileChooseBinding = DesignerItemMaterielDetaileChooseBinding.inflate(layoutInflater, parent, false)
}