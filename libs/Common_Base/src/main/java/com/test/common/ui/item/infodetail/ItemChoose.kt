package com.test.common.ui.item.infodetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseItemDetailChooseBinding

/**
 * 详情页面显示的信息
 */
class ItemChoose: QuickDataBindingItemBinder<ItemChooseEntity, BaseItemDetailChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseItemDetailChooseBinding>,
        data: ItemChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseItemDetailChooseBinding = BaseItemDetailChooseBinding.inflate(layoutInflater, parent, false)
}