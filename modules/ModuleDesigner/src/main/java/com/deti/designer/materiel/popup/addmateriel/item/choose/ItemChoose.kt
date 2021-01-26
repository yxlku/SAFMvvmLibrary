package com.deti.designer.materiel.popup.addmateriel.item.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.databinding.DesignerTiemAddMaterielChooseBinding
import com.deti.designer.materiel.MaterielListViewModel

/**
 * 选择模式
 */
class ItemChoose(
    var mViewModel: MaterielListViewModel
): QuickDataBindingItemBinder<ItemChooseEntity, DesignerTiemAddMaterielChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerTiemAddMaterielChooseBinding>,
        data: ItemChooseEntity,
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
    ): DesignerTiemAddMaterielChooseBinding = DesignerTiemAddMaterielChooseBinding.inflate(layoutInflater, parent, false)
}