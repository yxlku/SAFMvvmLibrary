package com.test.common.ui

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
    RIGHT(
        R.drawable.ic_baseline_delete_forever,
        DensityUtil.dp2px(10F),
        TextViewDrawableEnum.RIGHT
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