package com.deti.brand.demand.create.item.form

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemFormChooseBinding
import com.deti.brand.demand.create.CreateDemandViewModel

/**
 * 表单-选择类型
 */
class ItemFormChoose(
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemFormChooseEntity, BrandItemFormChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemFormChooseBinding>,
        data: ItemFormChooseEntity,
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
    ): BrandItemFormChooseBinding = BrandItemFormChooseBinding.inflate(layoutInflater, parent, false)
}