package com.deti.brand.demand.create.item.demandtype

data class ItemDeamandTypeChooseEntity(
    var showText: String = "",
    /** 选择输入信息类型*/
    var types: ArrayList<String> = arrayListOf()

)