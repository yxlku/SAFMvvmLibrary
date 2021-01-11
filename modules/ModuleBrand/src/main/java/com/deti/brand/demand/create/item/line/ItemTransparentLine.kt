package com.deti.brand.demand.create.item.line

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemLineTransparentBinding
import me.jessyan.autosize.utils.AutoSizeUtils

class ItemTransparentLine: QuickDataBindingItemBinder<ItemTransparentLineEntity, BrandItemLineTransparentBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemLineTransparentBinding>,
        data: ItemTransparentLineEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        var layoutParams = binding.clLayout.layoutParams
        layoutParams.apply {
            height = AutoSizeUtils.mm2px(context, data.height)
        }
        binding.clLayout.layoutParams = layoutParams
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemLineTransparentBinding = BrandItemLineTransparentBinding.inflate(layoutInflater, parent, false)
}