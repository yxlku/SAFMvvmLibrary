package com.safmvvm.ui.toast

import com.safmvvm.ext.TextViewDrawableEnum

/**
 * 默认自定义Toast显示图片
 */
class DefToastEnumInterface : ToastEnumInterface {
    override fun iconId(): Int = 0
    override fun drawablePadding(): Int = 0
    override fun drawableDirection(): TextViewDrawableEnum = TextViewDrawableEnum.LEFT
}