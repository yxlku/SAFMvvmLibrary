package com.safmvvm.utils

import android.app.Application
import com.safmvvm.app.BaseApp


/**
 * 屏幕分辨率计算转换
 */
object DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {
        return dp2px(BaseApp.getInstance(), dpValue)
    }

    fun dp2px(app: Application, dpValue: Float): Int {
        val scale = app.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(pxValue: Float): Int {
        return px2dp(BaseApp.getInstance(), pxValue)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(app: Application, pxValue: Float): Int {
        val scale = app.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = BaseApp.getInstance().resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = BaseApp.getInstance().resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}
