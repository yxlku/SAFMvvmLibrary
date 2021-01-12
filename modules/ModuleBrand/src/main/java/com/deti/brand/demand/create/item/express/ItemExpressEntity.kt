package com.deti.brand.demand.create.item.express

data class ItemExpressEntity(
    /** 选中快递类型item的Id*/
    var selectedExpressId: String = "",
    /** 选中快递类型item的文字*/
    var selectedExpressText: String = "",
)