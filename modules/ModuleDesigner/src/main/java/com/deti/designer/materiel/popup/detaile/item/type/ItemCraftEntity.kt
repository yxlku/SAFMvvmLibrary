package com.deti.designer.materiel.popup.detaile.item.type

import com.deti.designer.materiel.popup.detaile.CraftDeatileTypeEntity

data class ItemCraftEntity(
    /** 工艺详情数据*/
    var craftList: ArrayList<CraftDeatileTypeEntity> = arrayListOf()
)