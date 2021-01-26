package com.deti.designer.materiel.popup.addmateriel.item.input

import android.text.InputType
import java.io.Serializable

data class ItemInputEntity(
    var id: String = "",
    var title: String = "",
    var hint: String = "",
    var content: String = "",
    /** 是否显示分隔线*/
    var isShowLine: Boolean = true,
    /** 输入模式*/
    var inputType: Int = InputType.TYPE_CLASS_TEXT,
):Serializable