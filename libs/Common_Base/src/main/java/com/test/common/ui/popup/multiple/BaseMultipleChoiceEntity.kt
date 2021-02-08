package com.test.common.ui.popup.multiple

data class BaseMultipleChoiceEntity(
    var id: String = "",
    /** 显示文字*/
    var text: String,
    /** 是否选中*/
    var isSelected: Boolean = false
)