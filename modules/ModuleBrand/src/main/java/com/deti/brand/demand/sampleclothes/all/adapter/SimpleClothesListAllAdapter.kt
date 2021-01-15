package com.deti.brand.demand.sampleclothes.all.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.sampleclothes.all.entity.FirstNodeEntity
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity

class SimpleClothesListAllAdapter: BaseNodeAdapter() {
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