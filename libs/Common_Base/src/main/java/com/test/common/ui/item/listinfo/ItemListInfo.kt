package com.test.common.ui.item.listinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseItemListInfoBinding

/**
 * 列表信息
 */
class ItemListInfo: QuickDataBindingItemBinder<ItemListInfoEntity, BaseItemListInfoBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseItemListInfoBinding>,
        data: ItemListInfoEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseItemListInfoBinding = BaseItemListInfoBinding.inflate(layoutInflater, parent, false)
}