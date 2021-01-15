package com.deti.brand.demand.sampleclothes.all.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity

class SecondNodeProvider : BaseNodeProvider(){
    override val itemViewType: Int = 2

    override val layoutId: Int = R.layout.brand_item_simple_clothes_list_second

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity

    }

}