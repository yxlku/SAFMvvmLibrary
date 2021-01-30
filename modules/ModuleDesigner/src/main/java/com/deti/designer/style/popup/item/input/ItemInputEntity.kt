package com.deti.designer.style.popup.item.input

data class ItemInputEntity(
    var id: String = "",
    var title: String = "",
    var hint: String = "",
    var content: String = "",
    /** 是否显示分隔线*/
    var isShow: Boolean = true,
)