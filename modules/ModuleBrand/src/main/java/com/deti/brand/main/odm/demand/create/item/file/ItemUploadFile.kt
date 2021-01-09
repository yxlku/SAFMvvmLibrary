package com.deti.brand.main.odm.demand.create.item.file

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemUploadFileBinding

class ItemUploadFile: QuickDataBindingItemBinder<ItemUploadFileEntity, BrandItemUploadFileBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemUploadFileBinding>,
        data: ItemUploadFileEntity,
    ) {
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemUploadFileBinding = BrandItemUploadFileBinding.inflate(layoutInflater, parent, false)
}