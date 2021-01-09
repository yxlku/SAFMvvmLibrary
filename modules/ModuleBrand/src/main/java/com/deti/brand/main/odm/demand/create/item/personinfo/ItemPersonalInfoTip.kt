package com.deti.brand.main.odm.demand.create.item.personinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPersonalInfoTipBinding

class ItemPersonalInfoTip: QuickDataBindingItemBinder<ItemPersonalInfoEntity, BrandItemPersonalInfoTipBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPersonalInfoTipBinding>,
        data: ItemPersonalInfoEntity,
    ) {
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPersonalInfoTipBinding {
        return BrandItemPersonalInfoTipBinding.inflate(layoutInflater, parent, false)
    }
}