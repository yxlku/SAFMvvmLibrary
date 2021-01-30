package com.deti.designer.materiel.popup.detaile.item.choose

data class ItemChooseEntity(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var unit: String = "",
    var hint: String = "",
    var isShowLine: Boolean = true
)