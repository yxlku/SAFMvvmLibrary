package com.deti.designer.materiel.popup.addmateriel.item.btn

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemBtnBinding
import com.deti.designer.materiel.MaterielListViewModel

/**
 * 底部完成按钮
 */
class ItemBtn(
    var mViewModel: MaterielListViewModel
): QuickDataBindingItemBinder<ItemBtnEntity, DesignerItemBtnBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemBtnBinding>,
        data: ItemBtnEntity,
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
    ): DesignerItemBtnBinding = DesignerItemBtnBinding.inflate(layoutInflater, parent, false)
}