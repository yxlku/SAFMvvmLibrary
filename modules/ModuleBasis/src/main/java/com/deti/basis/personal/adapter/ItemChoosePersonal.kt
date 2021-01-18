package com.deti.basis.personal.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.basis.databinding.BasisItemPerfectChooseBinding

class ItemChoosePersonal(
    var mActivity: Activity?
): QuickDataBindingItemBinder<ItemChoosePersonalEntity, BasisItemPerfectChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BasisItemPerfectChooseBinding>,
        data: ItemChoosePersonalEntity,
    ) {
        var mViewModel = ItemChoosePersonalViewModel(mActivity, adapter, holder.adapterPosition)
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
    ): BasisItemPerfectChooseBinding = BasisItemPerfectChooseBinding.inflate(layoutInflater, parent, false)

}