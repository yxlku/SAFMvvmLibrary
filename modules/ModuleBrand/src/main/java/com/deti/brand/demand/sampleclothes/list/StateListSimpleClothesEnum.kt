package com.deti.brand.demand.sampleclothes.list

/**
 * 样衣列表-状态
 */
object StateListSimpleClothesEnum {
    /** 全部列表*/
    const val STATE_ALL: Int = 0
    /** 待发货*/
    const val STATE_DELIVER: Int = 1
    /** 待收货*/
    const val STATE_RECEIVED: Int = 2
    /** 待评价*/
    const val STATE_EVALUATION: Int = 3
    /** 待付款*/
    const val STATE_PAYMENT: Int = 4
}