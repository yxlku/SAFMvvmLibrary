package com.deti.brand.main.odm.demand.create.item.form

class ItemFormChooseEntity(
    /** item标题*/
    var title: String = "",
    /** 必填 * 是否显示*/
    var tipXShow: Boolean = false,
    /** 提示文字和选中*/
    var hintText: String = "请选择",
    /** 选择后的文字*/
    var contentText: String = "",
)