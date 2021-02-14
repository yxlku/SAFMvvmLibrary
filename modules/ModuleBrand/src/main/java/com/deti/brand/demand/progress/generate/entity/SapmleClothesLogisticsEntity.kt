package com.deti.brand.demand.progress.generate.entity

import java.io.Serializable

/**
 * 样衣物流
 */
data class SapmleClothesLogisticsEntity(
    /** 样衣进度 0（全灰） 、1 、2，3*/
    var status: String = "",
    /** 快递名称*/
    var expressName: String = "",
    /** 快递编号*/
    var expressCode: String = "",
    /** 快递进度*/
    var infoList: List<InfoListDataBean> = arrayListOf(),
): Serializable

data class InfoListDataBean(
    /** 时间*/
    var time: String = "",
    /** 快递进度文字*/
    var context: String = "",

): Serializable