package com.test.common.ui.popup.custom.sizecount.adapter.entity

import com.chad.library.adapter.base.entity.node.BaseNode
import java.io.Serializable

open class SecondNodeEntity(
    open var id: Int = 0,
    /** 尺寸*/
    open var size: String = "",
    /** 数量*/
    open var count: Int = 0,
    /** 颜色*/
    open var color: String = "",
    override val childNode: MutableList<BaseNode> = mutableListOf()
) : BaseNode(), Serializable