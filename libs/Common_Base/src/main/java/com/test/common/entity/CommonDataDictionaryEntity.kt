package com.test.common.entity

import java.io.Serializable

/**
 * 通用数据字典实体
 */
data class CommonDataDictionaryEntity(
    /**  ID*/
    var id: String = "",
    /** 编码*/
    var code: String = "",
    /** 名称*/
    var text: String = "",
    /** 排序码*/
    var orderCode: String = "",
    /** 备注*/
    var comment: String = "",
    /** 有效标志*/
    var iavailableFlagd: String = "",
): Serializable