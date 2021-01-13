package com.test.common.ui.dialog.color

import java.io.Serializable

data class ColorsEntity(
    var colorId: Int = 0,
    var colorText: String = "",
    var isCheck: Boolean = false,
    var colorsSubEntitys: List<ColorsSubEntity> = arrayListOf()
): Serializable

data class ColorsSubEntity(
    var colorId: Int = 0,
    var colorText: String = "",
    var isCheck: Boolean = false,
): Serializable
