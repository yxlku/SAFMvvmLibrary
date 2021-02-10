package com.test.common.ui.item.infotitle

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseItemDetailChooseBinding
import com.test.common.databinding.BaseItemDetailTitleBinding

/**
 * 详情页面显示的item标题
 */
class ItemInfoTitle: QuickDataBindingItemBinder<ItemInfoTitleEntity, BaseItemDetailTitleBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseItemDetailTitleBinding>,
        data: ItemInfoTitleEntity,
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
    ): BaseItemDetailTitleBinding = BaseItemDetailTitleBinding.inflate(layoutInflater, parent, false)
}