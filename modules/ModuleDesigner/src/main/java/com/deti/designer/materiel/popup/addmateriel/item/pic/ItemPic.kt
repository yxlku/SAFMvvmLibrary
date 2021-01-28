package com.deti.designer.materiel.popup.addmateriel.item.pic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemPicBinding
import com.deti.designer.materiel.MaterielListViewModel
import com.deti.designer.materiel.popup.addmateriel.PopupAddMaterielViewModel

/**
 * 上传图片
 */
class ItemPic(
    var mViewModel: PopupAddMaterielViewModel
): QuickDataBindingItemBinder<ItemPicEntity, DesignerItemPicBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemPicBinding>,
        data: ItemPicEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entiy = data
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemPicBinding = DesignerItemPicBinding.inflate(layoutInflater, parent, false)
}