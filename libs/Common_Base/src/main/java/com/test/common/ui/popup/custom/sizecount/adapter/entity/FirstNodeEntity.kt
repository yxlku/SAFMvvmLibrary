package com.test.common.ui.popup.custom.sizecount.adapter.entity

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

open class FirstNodeEntity(
    open var id: String = "",
    /** 颜色*/
    open var color: String = "",
    /** 数量*/
    open var count: Int = 0,
    /** 颜色代码*/
    open var colorCode: String = "",
    override val childNode: MutableList<BaseNode> = mutableListOf(),
) : BaseExpandNode()