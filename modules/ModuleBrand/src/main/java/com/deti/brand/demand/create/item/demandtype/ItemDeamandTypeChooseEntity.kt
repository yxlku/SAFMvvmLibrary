package com.deti.brand.demand.create.item.demandtype

/**
 * 类型选择
 */
data class ItemDeamandTypeChooseEntity(
    var showText: String = "",
    /** 选择输入信息类型*/
    var types: ArrayList<String> = arrayListOf()
)

