package com.test.common.ui.item.line

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseItemLineTransparentBinding
import me.jessyan.autosize.utils.AutoSizeUtils

class ItemTransparentLine: QuickDataBindingItemBinder<ItemTransparentLineEntity, BaseItemLineTransparentBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseItemLineTransparentBinding>,
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
    ): BaseItemLineTransparentBinding = BaseItemLineTransparentBinding.inflate(layoutInflater, parent, false)
}