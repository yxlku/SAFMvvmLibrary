package com.deti.brand.main.odm.demand.create.item.pic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPicChooseBinding

class ItemPicChoose: QuickDataBindingItemBinder<ItemPicChooseEntity, BrandItemPicChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPicChooseBinding>,
        data: ItemPicChooseEntity,
    ) {
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPicChooseBinding = BrandItemPicChooseBinding.inflate(layoutInflater, parent, false, null)
}