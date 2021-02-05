package com.deti.brand.demand.detail.entity

import java.io.Serializable

/**
 * 其他费用
 */
data class OtherCostEntity(
    var result: OtherCostResultEntity

): Serializable

data class OtherCostResultEntity(
    var id: String = "",
    /** 打板费*/
    var makeSamplePrice: Int = 0,
    /** 小单费*/
    var smallOrderPrice: Int = 0,
    /** 测试费用*/
    var testPrice: Int = 0,
    /** 运费和保险*/
    var transportPrice: Int = 0,
    /** 加工费*/
    var processPrice: Int = 0,
    /** 其他费用*/
    var otherPrice: Int = 0,
):Serializable