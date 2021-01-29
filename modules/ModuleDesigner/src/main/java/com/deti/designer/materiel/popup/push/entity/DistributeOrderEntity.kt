package com.deti.designer.materiel.popup.push.entity

import androidx.databinding.ObservableField

data class DistributeOrderEntity(
    var remark: ObservableField<String> = ObservableField(),
    /** 是否被选中*/
    var isChecked: Boolean = false
)