package com.safmvvm.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.safmvvm.app.BaseApp

/**
 * 资源统一调用类
 */
object ResUtil {

    /**
     * 通过id获取string中的值
     */
    fun getString(@StringRes strId: Int): String{
        try {
            return BaseApp.getInstance().getString(strId)
        } catch (e: Exception) {
            return ""
        }
    }

    /**
     * 通过资源id获取颜色
     */
    fun getColor(@ColorRes colorId: Int): Int{
        return ContextCompat.getColor(BaseApp.getInstance(), colorId)
    }

    /**
     * 获取图片资源
     */
    fun getDrawable(@DrawableRes imgId: Int): Drawable?{
        return ContextCompat.getDrawable(BaseApp.getInstance(), imgId)
    }
}