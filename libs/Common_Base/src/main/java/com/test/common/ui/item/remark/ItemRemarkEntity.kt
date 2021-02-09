package com.test.common.ui.item.remark

import androidx.databinding.ObservableField

class ItemRemarkEntity(
    var id:String ="",
    var title: String = "",
    var contentText: ObservableField<String> = ObservableField(""),
    var hintText: String = "",
)