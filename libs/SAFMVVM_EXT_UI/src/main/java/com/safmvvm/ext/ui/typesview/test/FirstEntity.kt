package com.safmvvm.ext.ui.typesview.test

import com.chad.library.adapter.base.entity.node.BaseNode
import com.safmvvm.ext.ui.typesview.entity.BaseTypesExpandTypesNode
import com.safmvvm.ext.ui.typesview.entity.BaseTypesNode

class FirstEntity(
    override val textContent: String,
    override val childNode: ArrayList<BaseTypesNode>?
) : BaseTypesExpandTypesNode() {
}

