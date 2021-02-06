package com.deti.brand.demand.create.item.express

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandExpressBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.safmvvm.ext.rvIsGone

/**
 * 快递信息
 */
class ItemExpress(
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemExpressEntity, BrandItemDemandExpressBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandExpressBinding>,
        data: ItemExpressEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            viewModel = mViewModel
            holder.itemView.rvIsGone(!data.isShow)
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandExpressBinding = BrandItemDemandExpressBinding.inflate(layoutInflater, parent, false)
}