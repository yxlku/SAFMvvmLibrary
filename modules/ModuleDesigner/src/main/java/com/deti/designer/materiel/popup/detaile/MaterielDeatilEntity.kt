package com.deti.designer.materiel.popup.detaile

import java.io.Serializable

interface TabEntity{
    var tabName: String
}

data class MaterielDeatilEntity(
    /** 物料类型数据*/
    var typeList: ArrayList<MaterielDeatilTypeEntity> = arrayListOf(),

    /** 工艺详情数据*/
    var craftList: ArrayList<CraftDeatileTypeEntity> = arrayListOf()

)

/**
 * 物料类型
 */
data class MaterielDeatilTypeEntity(
    /** 物料类型名称：主料、辅料*/
    var materielTypeName: String = "",
    /** tab对应的数据*/
    var materielTypeData: MaterielDeatilTypeData
): TabEntity {
    override var tabName: String = materielTypeName
}

/**
 * 物料详情 - 物料类型数据
 */
data class MaterielDeatilTypeData(
    /** 供应商名字*/
    var supplierName: String = "",
    /** 品名*/
    var productName: String = ""
): Serializable

/**
 * 工艺类型
 */
data class CraftDeatileTypeEntity(
    var craftTypeName: String = "",
    var craftTypeData: CraftDeatilTypeData
): TabEntity {
    override var tabName: String = craftTypeName
}

/**
 * 工艺详情
 */
data class CraftDeatilTypeData(
    var craftTGF: String = "",
):Serializable
