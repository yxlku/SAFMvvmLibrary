package com.safmvvm.ui.toast

import androidx.annotation.DrawableRes
import com.safmvvm.utils.weight.TextViewDrawableEnum

/**
 * 实现类： 枚举、普通类都可以
 */
interface ToastEnumInterface {

    @DrawableRes
    fun iconId(): Int

    fun drawablePadding(): Int

    fun drawableDirection(): TextViewDrawableEnum
}