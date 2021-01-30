package com.deti.designer.style.popup.item.radio

import java.io.Serializable

data class ItemRadioEntity(
    var id: String = "",
    var title: String = "",
    var radioDatas: List<ItemRadioData> = arrayListOf(),
    var isShowLine: Boolean = true
): Serializable

data class ItemRadioData(
    var id: String = "",
    var text: String = "",
): Serializable