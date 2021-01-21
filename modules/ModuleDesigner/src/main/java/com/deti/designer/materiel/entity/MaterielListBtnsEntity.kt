package com.deti.designer.materiel.entity

import androidx.annotation.DrawableRes
import com.deti.designer.R

data class MaterielListBtnsEntity(
    var id: Int = 0,
    var text: String = "",
    /** 按钮背景*/
    @DrawableRes var btnBg: Int = R.drawable.base_btn_gray_bg
)