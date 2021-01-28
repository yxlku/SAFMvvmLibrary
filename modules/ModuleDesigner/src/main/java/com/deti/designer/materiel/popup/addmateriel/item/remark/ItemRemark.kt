package com.deti.designer.materiel.popup.addmateriel.item.remark

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemRemarkBinding
import com.deti.designer.materiel.MaterielListViewModel
import com.deti.designer.materiel.popup.addmateriel.PopupAddMaterielViewModel

class ItemRemark(
    var mViewModel: PopupAddMaterielViewModel
): QuickDataBindingItemBinder<ItemRemarkEntity, DesignerItemRemarkBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemRemarkBinding>,
        data: ItemRemarkEntity,
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
    ): DesignerItemRemarkBinding = DesignerItemRemarkBinding.inflate(layoutInflater, parent, false)
}