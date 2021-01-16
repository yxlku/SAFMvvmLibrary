package com.deti.brand.demand.sampleclothes.all.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.sampleclothes.all.entity.FirstNodeEntity
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity

class SimpleClothesListAllAdapter(
    var mActivity: Activity?,
): BaseNodeAdapter() {
    companion object  {
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }
    init {
        addNodeProvider(FirstNodeProvider())
        addNodeProvider(SecondNodeProvider(mActivity))
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