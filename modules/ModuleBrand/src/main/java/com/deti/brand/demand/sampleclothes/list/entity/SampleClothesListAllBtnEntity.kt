package com.deti.brand.demand.sampleclothes.list.entity

import androidx.annotation.DrawableRes
import com.deti.brand.R


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
    @DrawableRes var btnBg: Int = R.drawable.base_btn_gray_bg

)