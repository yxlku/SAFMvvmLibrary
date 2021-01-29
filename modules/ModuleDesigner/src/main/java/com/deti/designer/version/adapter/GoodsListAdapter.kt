package com.deti.designer.version.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemVersionGoodsBinding
import com.deti.designer.version.entity.GoodsDataEntity

/**
 * 货号列表
 */
class GoodsListAdapter: BaseQuickAdapter<GoodsDataEntity, BaseDataBindingHolder<DesignerItemVersionGoodsBinding>>(
    R.layout.designer_item_version_goods
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemVersionGoodsBinding>,
        item: GoodsDataEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()
        }
    }
}