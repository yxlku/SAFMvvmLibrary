package com.safmvvm.ext.ui.typesview.test

import com.safmvvm.ext.ui.typesview.entity.BaseTypesExpandTypesNode
import com.safmvvm.ext.ui.typesview.entity.BaseTypesNode

class ThridEntity(
    override val textContent: String,
    override val childNode: ArrayList<BaseTypesNode>?,
) : BaseTypesExpandTypesNode() {
}