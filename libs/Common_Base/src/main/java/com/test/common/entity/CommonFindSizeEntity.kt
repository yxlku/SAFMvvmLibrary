package com.test.common.entity

import java.io.Serializable

/**
 * 通用尺码组实体类
 */
data class CommonFindSizeEntity(
    var list: List<CommonFindSizeDataBean> = arrayListOf()
): Serializable

data class CommonFindSizeDataBean(
    var id: String = "",
    /** 二级类别*/
    var category: String = "",
    /** 一级类别*/
    var gender: String = "",
    /** 三级类别*/
    var suitType: String = "",

    /** 尺码组名称*/
    var label: String = "",
    /** 尺码组*/
    var sizeRangeList: List<String> = arrayListOf(),
): Serializable