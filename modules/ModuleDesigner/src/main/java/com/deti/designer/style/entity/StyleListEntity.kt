package com.deti.designer.style.entity

import java.io.Serializable

/**
 * 款式列表适配器
 */
data class StyleListEntity(
    /** 款号*/
    var styleNum: String = "",
    var name: String = "",
    /** 版单列表*/
    var styleVersionDataList: ArrayList<StyleVersionData> = arrayListOf()
)

data class StyleVersionData(
    /** 货号*/
    var goodsNum: String = "",
    /** 名称*/
    var name: String = "",

): Serializable