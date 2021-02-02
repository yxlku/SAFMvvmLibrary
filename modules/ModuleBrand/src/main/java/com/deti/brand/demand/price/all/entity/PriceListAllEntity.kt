package com.deti.brand.demand.price.all.entity

import java.io.Serializable

/**
 * 品牌商侧-获取需求单列表(APP)
 */
data class DemandIndentListApp(
    var result: DemandIndentResult
):Serializable

data class DemandIndentResult(
    var list: ArrayList<PriceListAllEntity> = arrayListOf()
):Serializable

/**
 * 报价列表实体类
 */
data class PriceListAllEntity(
    var id: String = "",
    /** 需求单号*/
    var serialNumber: String = "",


    var state: String = "",
    /** 服务类型*/
    var productionType: String = ""
):Serializable