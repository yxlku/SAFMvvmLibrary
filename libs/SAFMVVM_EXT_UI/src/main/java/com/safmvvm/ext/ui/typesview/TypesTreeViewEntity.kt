package com.safmvvm.ext.ui.typesview

/**
 * typesview的实体类，无论调用者什么对象都要转换为此对象
 */
data class TypesTreeViewEntity(
    /**
     * 第一层如果为空，则显示空布局
     */
    var childer: List<TypesViewDataBean>? = null
)

open class TypesViewDataBean(
    open var id: String = "",
    /**
     * 显示的文字
     */
    var text: String = "",
    /**
     * 子列表，这个层级控制有基层rv
     */
    var childer: List<TypesViewDataBean>? = null
)