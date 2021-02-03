package com.deti.brand.demand.price.all.entity

import com.test.common.entity.CommonDeamndImageEntity
import java.io.Serializable

/**
 * 品牌商侧-获取需求单列表(APP)
 */
data class DemandIndentListApp(
    var pageData: DemandIndentResult
):Serializable

data class DemandIndentResult(
    var content: ArrayList<PriceListAllEntity> = arrayListOf(),
    var currentPage: String = "1",
    var totalElements: String = "1",
    var totalPage: String = "1"
):Serializable

/**
 * 报价列表实体类
 */
data class PriceListAllEntity(
    var id: String = "",
    /** 款式*/
    var classifyName: String = "",
    /** 颜色*/
    var colorStr: String = "",
    /** 交货日期  -- 需求单截止时间*/
    var deliveryTime: String = "",
    /** 需求单id*/
    var indentId: String = "",
    /** 下单时间*/
    var orderTime: String = "",
    /** 下单时间戳*/
    var orderTimestamp: String = "",
    /** 报价单id*/
    var quoteId: String = "",
    /** 报价*/
    var quotePrice: String = "",
    /**
     *  未签收 - receiveFlag ==20显示样衣文字提示
        已签收 - receiveFlag ==30 显示倒计时
        无样衣 - receiveFlag ==10 无样衣-直接倒计时，并且不显示签收时间字段，显示下单时间字段
     */
    var receiveFlag: String = "",
    /** 签收时间*/
    var receiveTime: String = "",
    /** 签收时间戳*/
    var receiveTimestamp: String = "",
    /** 需求单号*/
    var serialNumber: String = "",
    /** 服务类型*/
    var service: String = "",
    /** 服务类型 - key*/
    var serviceType: String = "",
    /** 1待得体报价 2得体报价中 3待确认 5需求已取消 10已确认报价 11已拒绝报价*/
    var status: String = "",
    /** 状态名字：得体报价中。。。。*/
    var statusName: String = "",
    /** 预算*/
    var price: String = "",

    /** 样衣提示: 样衣签收后开始计时*/
    var prompt: String = "",

    /** 图片列表*/
    var images: List<CommonDeamndImageEntity> = arrayListOf()
):Serializable

