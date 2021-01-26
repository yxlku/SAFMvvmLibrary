package com.test.common.adapter

import androidx.annotation.DrawableRes
import com.test.common.R

data class CommonListBtnsEntity(
    var id: String = "",
    var text: String = "",
    /** 按钮背景*/
    @DrawableRes var btnBg: Int = R.drawable.base_btn_gray_bg
)