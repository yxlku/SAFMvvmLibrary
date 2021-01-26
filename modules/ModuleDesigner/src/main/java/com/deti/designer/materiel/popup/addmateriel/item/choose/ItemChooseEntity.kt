package com.deti.designer.materiel.popup.addmateriel.item.choose

import java.io.Serializable

data class ItemChooseEntity(
    var id: String = "",
    /** 标题*/
    var title: String = "",
    /** 提示文字*/
    var hint: String = "",
    /** 选择后的内容*/
    var content: String = "",
    /** 是否显示分隔线*/
    var isShowLine: Boolean = true,
): Serializable