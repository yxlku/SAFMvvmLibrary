package com.test.common.entity

import java.io.Serializable

/**
 * 通用实体：尺寸与数量
 */
data class CommonSizeCountEntity(
    /** 尺码*/
    var size: String = "",
    var sizeName: String = "",
    /** 数量*/
    var count: Int = 0
): Serializable