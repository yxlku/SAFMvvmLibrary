package com.test.common.ui.popup.custom.sizecount.adapter

import android.app.Activity
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.test.common.R
import com.test.common.ui.popup.custom.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.popup.custom.sizecount.adapter.entity.SecondNodeEntity

class SizeCountAdapter(
    var mActivity: Activity,
    @LayoutRes var mLayoutId: Int = R.layout.base_dialog_item_sizecount_first,
) : BaseNodeAdapter() {
    companion object  {
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }
    init {
        var first = FirstNodeProvider(mActivity, mLayoutId)
        addFullSpanNodeProvider(first)
        addNodeProvider(SecondNodeProvider{
            //子列表加减数据刷新列表，达到更新一级列表的数据-数量
            notifyDataSetChanged()
        })
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