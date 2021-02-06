package com.deti.brand.demand.detail.entity

import java.io.Serializable

/**
 * 合计报价
 */
data class TotalCostEntity(
    var result: TotalCostResultEntity
):Serializable

data class TotalCostResultEntity(
    /** 成本*/
    var costPrice: String = "",
    /** */
    var demandId: String = "",
    /** 第五阶梯费用*/
    var fifthTieredPrice: String = "",
    /** 第一阶梯费用*/
    var firstTieredPrice: String = "",
    /** 第四阶梯费用*/
    var fourthTieredPrice: String = "",
    /** 物料合计*/
    var materialPrice: String = "",
    /** 其他价格*/
    var otherPrice: String = "",
    /** 工序价格*/
    var processPrice: String = "",
    /** 利润*/
    var profit: String = "",
    /** 安全技术要求*/
    var safetyCategory: String = "",
    /** 第二阶梯费用*/
    var secondTieredPrice: String = "",
    /** 第六阶梯费用*/
    var sixthTieredPrice: String = "",
    /** 测试标准*/
    var standard: String = "",
    /** 第三阶梯费用*/
    var thirdTieredPrice: String = "",
    /** 增值税*/
    var valueAddTax: String = "",
    /** 报价时间*/
    var quoteTime: String = "",
    /** 特殊工艺*/
    var technologyPrice: String = "",
): Serializable