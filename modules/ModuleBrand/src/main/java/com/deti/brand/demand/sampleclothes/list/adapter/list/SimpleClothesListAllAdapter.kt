package com.deti.brand.demand.sampleclothes.list.adapter.list

import android.app.Activity
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.demand.sampleclothes.list.StateListSimpleClothesEnum
import com.deti.brand.demand.sampleclothes.list.entity.FirstNodeEntity
import com.deti.brand.demand.sampleclothes.list.entity.SecondNodeEntity

/**
 * 样衣订单两级列表适配器
 */
class SimpleClothesListAllAdapter(
    var mActivity: Activity?,
    /** 列表状态*/
    var mStateList: Int = StateListSimpleClothesEnum.STATE_ALL
): BaseNodeAdapter() {
    companion object  {
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }
    init {
        addFullSpanNodeProvider(FirstNodeProvider())
        addNodeProvider(SecondNodeProvider(mActivity, mStateList))
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