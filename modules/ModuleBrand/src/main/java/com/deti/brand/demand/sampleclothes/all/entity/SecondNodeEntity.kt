package com.deti.brand.demand.sampleclothes.all.entity

import androidx.annotation.DrawableRes
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

data class SecondNodeEntity(
    var id: Int = 0,
    var state: Int = 0,
    override val childNode: MutableList<BaseNode>? = null,
) : BaseExpandNode()

/**
 * 列表中按钮实体类
 */
data class PriceListAllBtnEntity(
    var id: Int = 0,
    /** 按钮文字*/
    var text: String,
    /** 状态*/
    var status: Int = 0,
    /** 按钮背景*/
    @DrawableRes var btnBg: Int = 0

)