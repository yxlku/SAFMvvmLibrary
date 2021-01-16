package com.deti.brand.demand.sampleclothes.all.entity

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

class FirstNodeEntity(
    var id: Int = 0,
    /** 订单数量*/
    var orderNumText: String = "",
    /** 款式数量*/
    var styleCount: Int = 0,
    override val childNode: MutableList<BaseNode>,
) : BaseExpandNode()