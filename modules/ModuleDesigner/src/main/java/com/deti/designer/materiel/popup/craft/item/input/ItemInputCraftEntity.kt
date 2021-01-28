package com.deti.designer.materiel.popup.craft.item.input

import java.io.Serializable

data class ItemInputCraftEntity(
    var id: String = "",
    /** 标题*/
    var title: String = "",
    /** 提示文字*/
    var hint: String = "",
    /** 选择后的内容*/
    var content: String = "",
    /** 单位*/
    var unit: String = "",
    /** 是否显示分隔线*/
    var isShowLine: Boolean = true,
): Serializable