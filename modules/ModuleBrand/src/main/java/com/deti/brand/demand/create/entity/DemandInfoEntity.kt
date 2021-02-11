package com.deti.brand.demand.create.entity

import com.test.common.entity.CommonColorEntity
import java.io.Serializable

/**
 * 订单信息 - 修改前获取的
 */
data class DemandInfoEntity(
    var demandIndent: DemandIntentEntity? = null
) : Serializable

data class DemandIntentEntity(
    var brandId: String = "",
    /** 对应服务 -- TODO 这两个字段名字是反的*/
    var serviceType: String = "",
    /** 服务类型*/
    var productionType: String = "",
    /** 面料信息*/
    var fabricInfo: String = "",
    /** 样衣快递类型*/
    var sampleDressExpressType: String = "",
    /** 快递单单号*/
    var sampleDressExpressId: String = "",
    /** 制版文件*/
    var makeFilePath: String = "",
    /** 正面图片*/
    var frontImage: String = ""
    /** 背面图片*/,
    var backImage: String = "",
    /** 详细图片列表*/
    var detailsImageList: List<String> = arrayListOf(),
    /** 性别-款式分类一级*/
    var gender: String = "",
    /** 品类-款式分类二级*/
    var category: String = "",
    /** 套装类型-款式分类三级*/
    var suitType: String = "",
    /** 款式分类*/
    var classify: String = "",
    /** 单价*/
    var unitPrice: String = "",
    /** 交期*/
    var deliveryDate: String = "",
    /** 备注*/
    var comment: String = "",
    /** 尺码组ID*/
    var sizeId: String = "",
    /** 颜色列表*/
    var colorList: ArrayList<CommonColorEntity> = arrayListOf(),
) : Serializable