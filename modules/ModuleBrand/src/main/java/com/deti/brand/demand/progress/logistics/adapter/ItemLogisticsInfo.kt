package com.deti.brand.demand.progress.logistics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemLogisticsInfoBinding

class ItemLogisticsInfo: QuickDataBindingItemBinder<ItemLogisticsInfoEntity, BrandItemLogisticsInfoBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemLogisticsInfoBinding>,
        data: ItemLogisticsInfoEntity
    ) {
        var databindg = holder.dataBinding
        databindg?.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BrandItemLogisticsInfoBinding = BrandItemLogisticsInfoBinding.inflate(layoutInflater, parent, false)
}