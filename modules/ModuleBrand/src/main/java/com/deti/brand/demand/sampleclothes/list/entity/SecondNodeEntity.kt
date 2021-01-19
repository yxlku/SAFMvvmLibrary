package com.deti.brand.demand.sampleclothes.list.entity

import androidx.annotation.DrawableRes
import com.chad.library.adapter.base.entity.node.BaseNode

class SecondNodeEntity(
    var id: Int = 0,
    var state: Int = 0,
    override val childNode: MutableList<BaseNode>? = null,
) : BaseNode()

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