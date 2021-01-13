package com.safmvvm.ext.ui.typesview.test

import com.chad.library.adapter.base.entity.node.BaseNode
import com.safmvvm.ext.ui.typesview.entity.BaseTypesNode

/**
 * 根节点 传值为null即可
 */
class FourEntity(override val textContent: String, override val childNode: ArrayList<BaseTypesNode>?) : BaseTypesNode() {
}