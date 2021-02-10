package com.test.common.ui.item.remark

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseItemRemarkBinding

/**
 * 备注
 */
class ItemRemark: QuickDataBindingItemBinder<ItemRemarkEntity, BaseItemRemarkBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseItemRemarkBinding>,
        data: ItemRemarkEntity,
    ) {
        holder.dataBinding.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseItemRemarkBinding = BaseItemRemarkBinding.inflate(layoutInflater, parent, false)
}