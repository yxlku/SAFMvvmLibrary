package com.test.common.ui.dialog.sizecount.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class SizeCountAdapter : BaseNodeAdapter() {
    companion object  {
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }
    init {
        addItemProvider(FirstNodeProvider())
        addItemProvider(SecondNodeProvider())
    }
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        var node: BaseNode = data[position]
        return when (node) {
            is FirstNodeEntity -> 1
            is SecondNodeEntity -> 2
            else -> -1
        }
    }


}