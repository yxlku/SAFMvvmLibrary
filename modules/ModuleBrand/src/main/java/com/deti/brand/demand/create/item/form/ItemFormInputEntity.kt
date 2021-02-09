package com.deti.brand.demand.create.item.form

import androidx.databinding.ObservableField

class ItemFormInputEntity(
    /** item标题*/
    var title: String = "",
    /** 必填 * 是否显示*/
    var tipXShow: Boolean = false,
    /** 提示文字和选中*/
    var hintText: String = "请选择",
    /** 输入的文字*/
    var contentText: ObservableField<String> = ObservableField(""),
    /** 单位文字*/
    var unitText: String =""
)