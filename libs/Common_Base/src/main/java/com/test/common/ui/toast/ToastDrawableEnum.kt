package com.test.common.ui.toast

import androidx.annotation.DrawableRes
import com.safmvvm.ui.toast.ToastEnumInterface
import com.safmvvm.utils.DensityUtil
import com.safmvvm.utils.weight.TextViewDrawableEnum
import com.test.common.R

enum class ToastDrawableEnum(
    @DrawableRes var mIconId: Int,     //图片id
    var mDrawablePadding: Int,   //图片与文字间距
    var mDrawableDirection: TextViewDrawableEnum  //方向
): ToastEnumInterface {
    FAIL(
        R.drawable.base_toast_fail,
        DensityUtil.dp2px(10F),
        TextViewDrawableEnum.LEFT
    ) {
        override fun iconId(): Int {
            return mIconId
        }

        override fun drawablePadding(): Int {
            return mDrawablePadding
        }

        override fun drawableDirection(): TextViewDrawableEnum {
            return mDrawableDirection
        }
    },
    TOP(
        R.drawable.base_toast_top,
        DensityUtil.dp2px(10F),
        TextViewDrawableEnum.TOP
    ) {
        override fun iconId(): Int {
            return mIconId
        }

        override fun drawablePadding(): Int {
            return mDrawablePadding
        }

        override fun drawableDirection(): TextViewDrawableEnum {
            return mDrawableDirection
        }
    },
}