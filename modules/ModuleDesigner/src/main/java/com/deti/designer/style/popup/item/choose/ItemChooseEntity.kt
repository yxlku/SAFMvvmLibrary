package com.deti.designer.style.popup.item.choose

data class ItemChooseEntity(
    var id: String = "",
    var title: String = "",
    var hint: String = "",
    var content: String = "",
    /** 是否显示分隔线*/
    var isShow: Boolean = true,
)