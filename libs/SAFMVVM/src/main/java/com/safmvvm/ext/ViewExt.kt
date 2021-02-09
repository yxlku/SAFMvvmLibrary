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
 * @param defIsGone isGone为空时使用的值
 */
fun View.rvIsShow(isShow: Boolean?, defIsShow: Boolean = false){
    isShow?.apply {
        if (this@rvIsShow is ViewGroup) {
            for(i in 0 until this@rvIsShow.childCount){
                var v = this@rvIsShow.getChildAt(i)
                v.visibility = if (this) {
                    View.VISIBLE
                }else{
                    View.GONE
                }
            }
            this@rvIsShow.visibility = if(this) View.VISIBLE else View.GONE
        }else{
            //如果不是ViewGroup直接隐藏该View即可
            this@rvIsShow.visibility = View.GONE
        }
    } ?: apply {
        //如果isGone为空则直接强制设置为false
        rvIsShow(defIsShow)
    }
    requestLayout()

}
