package com.deti.brand.demand.sampleclothes.all.entity

import androidx.annotation.DrawableRes


/**
 * 列表中按钮实体类
 */
data class SampleClothesListAllBtnEntity(
    var id: Int = 0,
    /** 按钮文字*/
    var text: String,
    /** 状态*/
    var status: Int = 0,
    /** 按钮背景*/
    @DrawableRes var btnBg: Int = 0

)