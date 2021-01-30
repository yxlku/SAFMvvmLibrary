package com.deti.designer.materiel.popup.detaile.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.databinding.DesignerItemMaterielDetailRemarkBinding

class ItemRemark: QuickDataBindingItemBinder<ItemRemarksEntity, DesignerItemMaterielDetailRemarkBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemMaterielDetailRemarkBinding>,
        data: ItemRemarksEntity,
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
    ): DesignerItemMaterielDetailRemarkBinding = DesignerItemMaterielDetailRemarkBinding.inflate(layoutInflater, parent, false)
}