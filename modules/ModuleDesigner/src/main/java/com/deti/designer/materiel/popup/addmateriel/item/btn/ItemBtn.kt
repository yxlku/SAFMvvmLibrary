package com.deti.designer.materiel.popup.addmateriel.item.btn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemBtnBinding
import com.deti.designer.materiel.MaterielListViewModel
import com.deti.designer.materiel.popup.addmateriel.PopupAddMaterielViewModel
import com.deti.designer.materiel.popup.craft.AddCraftFragment

/**
 * 底部完成按钮
 */
class ItemBtn(
    var mActivity: AppCompatActivity,
    var mViewModel: PopupAddMaterielViewModel
): QuickDataBindingItemBinder<ItemBtnEntity, DesignerItemBtnBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemBtnBinding>,
        data: ItemBtnEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            viewModel = mViewModel
            tvAdd.setOnClickListener{
                AddCraftFragment().show(mActivity.supportFragmentManager, "")
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemBtnBinding = DesignerItemBtnBinding.inflate(layoutInflater, parent, false)
}