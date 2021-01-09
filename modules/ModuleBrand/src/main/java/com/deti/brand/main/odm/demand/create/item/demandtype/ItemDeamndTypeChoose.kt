package com.deti.brand.main.odm.demand.create.item.demandtype

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandTypeChooseBinding

class ItemDeamndTypeChoose: QuickDataBindingItemBinder<ItemDeamandTypeChooseEntity, BrandItemDemandTypeChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandTypeChooseBinding>,
        data: ItemDeamandTypeChooseEntity,
    ) {

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandTypeChooseBinding  = BrandItemDemandTypeChooseBinding.inflate(layoutInflater, parent, false)
}