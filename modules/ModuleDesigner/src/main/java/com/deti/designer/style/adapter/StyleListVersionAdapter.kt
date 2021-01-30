package com.deti.designer.style.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemStyleListItemVersionBinding
import com.deti.designer.style.entity.StyleVersionData

class StyleListVersionAdapter: BaseQuickAdapter<StyleVersionData, BaseDataBindingHolder<DesignerItemStyleListItemVersionBinding>>(
    R.layout.designer_item_style_list_item_version
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemStyleListItemVersionBinding>,
        item: StyleVersionData,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()
        }
    }
}