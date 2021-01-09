package com.deti.brand.main.odm.demand.create.item.line

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemLineGrayBinding
import me.jessyan.autosize.utils.AutoSizeUtils

class ItemGrayLine: QuickDataBindingItemBinder<ItemGrayLineEntity, BrandItemLineGrayBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemLineGrayBinding>,
        data: ItemGrayLineEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        var layoutParams = binding.vLine.layoutParams
        layoutParams.apply {
            height = AutoSizeUtils.mm2px(context, data.height)
        }
        binding.vLine.layoutParams = layoutParams
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemLineGrayBinding = BrandItemLineGrayBinding.inflate(layoutInflater, parent, false)
}