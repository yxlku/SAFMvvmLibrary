package com.deti.designer.materiel.popup.detaile

import java.io.Serializable

data class MaterielDeatilEntity(
    /** 物料类型数据*/
    var typeList: ArrayList<MaterielDeatilTypeEntity> = arrayListOf()

)

data class MaterielDeatilTypeEntity(
    /** 物料类型名称：主料、辅料*/
    var materielTypeName: String = "",
    /** tab对应的数据*/
    var materielTypeData: MaterielDeatilTypeData
)

/**
 * 物料详情 - 物料类型数据
 */
data class MaterielDeatilTypeData(
    /** 供应商名字*/
    var supplierName: String = "",
    /** 品名*/
    var productName: String = ""
): Serializable