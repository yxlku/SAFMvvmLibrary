package com.safmvvm.ext.ui.typesview

import java.io.Serializable

/**
 * typesview的实体类，无论调用者什么对象都要转换为此对象
 */
open class TypesTreeViewEntity(
    /**
     * 第一层如果为空，则显示空布局
     */
    open var childer: List<TypesViewDataBean>? = null
):Serializable

open class TypesViewDataBean(
    open var id: String = "",
    /**
     * 显示的文字
     */
    open var text: String = "",
    /**
     * code
     */
    open var code: String = "",
    /**
     * 子列表，这个层级控制有基层rv
     */
    open var childer: List<TypesViewDataBean>? = null
):Serializable