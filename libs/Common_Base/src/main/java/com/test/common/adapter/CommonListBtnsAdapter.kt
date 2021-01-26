package com.test.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseItemListBtnsBinding

/**
 * 列表按钮
 */
class CommonListBtnsAdapter: BaseQuickAdapter<CommonListBtnsEntity, BaseDataBindingHolder<BaseItemListBtnsBinding>>(
    R.layout.base_item_list_btns
) {
    override fun convert(
        holder: BaseDataBindingHolder<BaseItemListBtnsBinding>,
        item: CommonListBtnsEntity,
    ) {
        var databind = holder.dataBinding
        databind?.apply {
            entity = item
            executePendingBindings()
        }
    }
}