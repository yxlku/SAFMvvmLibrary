package com.deti.brand.demand.create.item.pic

data class ItemPicChooseEntity(
    var pics: ArrayList<ItemPicChooseItemEntity> = arrayListOf(),
)

data class ItemPicChooseItemEntity(
    var tipText: String = "",
    var picPath: String = ""
)