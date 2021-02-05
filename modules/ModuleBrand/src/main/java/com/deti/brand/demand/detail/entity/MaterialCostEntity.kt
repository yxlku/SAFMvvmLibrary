package com.deti.brand.demand.detail.entity

import com.test.common.ui.adapter.tab.IAdapterTabEntity
import com.test.common.ui.adapter.tab.IAdapterTabMode
import java.io.Serializable

/**
 * 物料详情
 */
data class MaterialCostEntity(
    var list: MaterialCostDetailEntity
): Serializable

/**
 * 物料详情信息
 */
data class MaterialCostDetailEntity(
    var id: String = "",
    var fabricList: ArrayList<MaterialCostItemEntity> = arrayListOf()
):Serializable

/**
 * 详情的具体信息
 */
data class MaterialCostItemEntity(
    var id: String = "",
    /** 品名 + index*/
    var name: String = "",
    /** 位置*/
    var index: String = "",
    /** 幅宽*/
    var breadth: String = "",
    /** 成份*/
    var ingredient: String = "",
    /** 损耗*/
    var lossPercent: String = "",
    /** 颜色*/
    var color: String = "",
    /** 颜号*/
    var colorNumber: String = "",
    /** 用量*/
    var singleQuantity: String = "",
    /** 单位*/
    var unit: String = "",
    /** 经缩*/
    var shrinkLongPercent: String = "",
    /** 纬缩*/
    var shrinkLatPercent: String = "",
    /** 总用量*/
    var totalQuantity: String = "",
    /** 单价*/
    var unitPrice: String = "",
    /** 总价*/
    var totalPrice: String = "",

    /** 工艺信息*/
    var attributes: MaterialCostCraftInfoEntity,
):Serializable, IAdapterTabEntity{
    override var tabName: String
        get() = name + index
        set(value) {}
    override var mode: IAdapterTabMode = IAdapterTabMode.MODE_BG
}

/**
 * 工艺信息
 */
data class MaterialCostCraftInfoEntity(
    var technology: ArrayList<CraftInfoEntity> = arrayListOf()
): Serializable

/**
 * 工艺具体信息
 */
data class CraftInfoEntity(
    var id: String = "",
): Serializable