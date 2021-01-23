package com.test.common.entity

import java.io.Serializable

/**
 * 颜色实体类
 */
data class CommonColorEntity(
    /** 尺码组ID*/
    var sizeId: String? = "",
    /** 颜色*/
    var name: String = "",
    /** 颜色代码*/
    var colorCode: String = "",
    /** 尺码数量列表*/
    var sizeToCountList: List<CommonSizeCountEntity> = arrayListOf(),
): Serializable