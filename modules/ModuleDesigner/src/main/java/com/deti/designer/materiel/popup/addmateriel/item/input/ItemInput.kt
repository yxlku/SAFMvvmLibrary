package com.deti.designer.materiel.popup.addmateriel.item.input

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerTiemAddMaterielInputBinding
import com.deti.designer.materiel.MaterielListViewModel


/**
 * Item 输入
 */
class ItemInput(
    var mViewModel: MaterielListViewModel,
): QuickDataBindingItemBinder<ItemInputEntity, DesignerTiemAddMaterielInputBinding>(){
    override fun convert(
        holder: BinderDataBindingHolder<DesignerTiemAddMaterielInputBinding>,
        data: ItemInputEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            viewModel = mViewModel
            etContent.inputType = data.inputType
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerTiemAddMaterielInputBinding = DesignerTiemAddMaterielInputBinding.inflate(layoutInflater, parent, false)
}