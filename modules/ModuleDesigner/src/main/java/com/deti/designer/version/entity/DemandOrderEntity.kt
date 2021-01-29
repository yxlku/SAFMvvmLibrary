package com.deti.designer.version.entity

/**
 * 版单列表
 */
data class DemandOrderEntity(
    var goosList: List<GoodsDataEntity> = arrayListOf()
)

data class GoodsDataEntity(
    var img: String = "",
    /** 名称*/
    var name: String = ""
)