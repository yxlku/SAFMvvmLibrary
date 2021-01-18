package com.test.common.ui.dialog.sizecount.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.test.common.R
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class SizeCountAdapter(
    @LayoutRes var mLayoutId: Int = R.layout.base_dialog_item_sizecount_first
) : BaseNodeAdapter() {
    companion object  {
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }
    init {
        addItemProvider(FirstNodeProvider(mLayoutId))
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