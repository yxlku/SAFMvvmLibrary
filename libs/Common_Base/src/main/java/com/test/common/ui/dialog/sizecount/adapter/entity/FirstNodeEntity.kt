package com.test.common.ui.dialog.sizecount.adapter.entity

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

class FirstNodeEntity(
    var id: String = "",
    /** 颜色*/
    var color: String = "",
    /** 数量*/
    var count: Int = 0,
    /** 颜色代码*/
    var colorCode: String = "",
    override val childNode: MutableList<BaseNode>,
) : BaseExpandNode()