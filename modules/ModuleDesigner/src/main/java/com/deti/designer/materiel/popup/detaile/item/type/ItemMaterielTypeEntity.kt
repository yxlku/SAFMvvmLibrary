package com.deti.designer.materiel.popup.detaile.item.type

import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeEntity

data class ItemMaterielTypeEntity(
    /** 物料类型数据*/
    var typeList: ArrayList<MaterielDeatilTypeEntity> = arrayListOf()
)