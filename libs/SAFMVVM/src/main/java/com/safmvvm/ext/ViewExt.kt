package com.safmvvm.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.isGone
import com.safmvvm.utils.DensityUtil
import com.safmvvm.utils.ResUtil
import java.lang.RuntimeException


/**
 * textView设置 drawable图片
 */
fun TextView.setDrawableImg(
    @DrawableRes drawableId: Int,
    drawablePadding: Int = DensityUtil.dp2px(10F),
    direction: TextViewDrawableEnum = TextViewDrawableEnum.LEFT
) {
    val drawable: Drawable? = ResUtil.getDrawable(drawableId)
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight) //设置边界
        when (direction) {
            TextViewDrawableEnum.LEFT -> this.setCompoundDrawables(drawable, null, null, null) //图片方向
            TextViewDrawableEnum.RIGHT -> this.setCompoundDrawables(null, null, drawable, null) //图片方向
            TextViewDrawableEnum.TOP -> this.setCompoundDrawables(null, drawable, null, null) //图片方向
            TextViewDrawableEnum.BOTTOM -> this.setCompoundDrawables(null, null, null, drawable) //图片方向
        }
        this.compoundDrawablePadding = drawablePadding
    } ?: run {
        throw RuntimeException("drawableId无效")
    }
}

/**
 * adapter控制显示隐藏（会将view中的子View一并隐藏，然后重绘制view，从而到达，adapter中隐藏后不留白）
 * @param isGone 是否隐藏，true为隐藏
 */
fun View.rvIsGone(isGone: Boolean){
    if (this is ViewGroup) {
        for(i in 0 until this.childCount){
            var v = this.getChildAt(i)
            v.visibility = if (isGone) {
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }
    requestLayout()

}
