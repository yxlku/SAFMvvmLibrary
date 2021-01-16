package com.test.common.ui.dialog.sizecount.adapter.entity

import com.chad.library.adapter.base.entity.node.BaseNode

class SecondNodeEntity(
    var id: Int = 0,
    /** 尺寸*/
    var size: String = "",
    /** 数量*/
    var count: Int,
    /** 颜色*/
    var color: String = "",
    override val childNode: MutableList<BaseNode>? = null,
) : BaseNode()