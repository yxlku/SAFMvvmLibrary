package com.test.common.ui.popup.custom.color

import java.io.Serializable

/**
 * 颜色列表
 */
data class DemandColorListEntity(
    var pageData: List<DemandColorListDataBean>? = null
):Serializable

data class DemandColorListDataBean(
    /** 色系code*/
    var code: String ="",
    /** 排序*/
    var orderCode: String = "",
    /** 色系拼音*/
    var pinyin: String = "",
    /** 色系名*/
    var text: String = "",
    /** 颜色列表*/
    var children: List<DemandColorDataBean>? = null
):Serializable


data class DemandColorDataBean(
    var id: String = "",
    /** 颜色编码*/
    var code: String = "",
    /** 颜色码*/
    var hex: String = "",
    /** 颜色名*/
    var name: String = "",
    /** 拼音*/
    var pinyin: String = "",
    /** 颜色系码*/
    var seriesCode: String = "",
    /** 是否被选中*/
    var mIsCheck: Boolean = false,
): Serializable
