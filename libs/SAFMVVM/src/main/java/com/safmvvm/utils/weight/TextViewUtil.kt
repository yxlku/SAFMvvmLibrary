package com.safmvvm.utils.weight

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.safmvvm.app.BaseApp
import com.safmvvm.utils.DensityUtil
import com.safmvvm.utils.ResUtil
import java.lang.RuntimeException

object TextViewUtil {

    fun setDrawableImg(
        tv: TextView,
        @DrawableRes drawableId: Int,
        drawablePadding: Int = DensityUtil.dp2px(10F),
        direction: TextViewDrawableEnum = TextViewDrawableEnum.LEFT
    ) {
        val drawable: Drawable? = ResUtil.getDrawable(drawableId)
        drawable?.let {
            drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight) //设置边界
            when (direction) {
                TextViewDrawableEnum.LEFT -> tv.setCompoundDrawables(drawable, null, null, null) //图片方向
                TextViewDrawableEnum.RIGHT -> tv.setCompoundDrawables(null, null, drawable, null) //图片方向
                TextViewDrawableEnum.TOP -> tv.setCompoundDrawables(null, drawable, null, null) //图片方向
                TextViewDrawableEnum.BOTTOM -> tv.setCompoundDrawables(null, null, null, drawable) //图片方向
            }
            tv.compoundDrawablePadding = drawablePadding
        } ?: run {
            throw RuntimeException("drawableId无效")
        }
    }

}

