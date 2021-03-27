package com.safmvvm.ui.theme

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.safmvvm.R
import com.safmvvm.ui.titlebar.TitleBar

/**
 * 状态栏工具
 */
object StatusBarUtil {

    /**
     * 初始化沉浸式状态栏
     */
    fun init(activity: Activity, isDark: Boolean = true){
        statusTextAndIconColorIsDark(activity, isDark, false)
    }

    /**
     * 初始化沉浸式状态栏
     */
    fun init(fragment: Fragment, isDark: Boolean = true){
        fragment.immersionBar{
            ImmersionBar.with(fragment)
                .keyboardEnable(true)
                .navigationBarColor(R.color.transparent)
                .statusBarDarkFont(isDark, 0.2f)
                .init()
        }
    }

    fun immersionPageView(activity: Activity, view: View){
        ImmersionBar.setTitleBar(activity, view)
    }
    fun immersionPageView(fragment: Fragment, view: View){
        ImmersionBar.setTitleBar(fragment, view)
    }
    fun statusTextAndIconColorIsDark(
        activity: Activity,
        isDark: Boolean,
        isKeyBoardEnable: Boolean = false,
    ){
        activity.immersionBar{
            //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）            transparentBar()
            transparentStatusBar()
            navigationBarColor(android.R.color.transparent) //导航栏颜色，不写默认黑色
            //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
            keyboardEnable(isKeyBoardEnable)
            //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
            statusBarDarkFont(isDark, 0.2f)
            init()
        }
    }

    /** 获取状态栏高度*/
    fun getStatusBarHeight(activity: Activity): Int = ImmersionBar.getStatusBarHeight(activity)
    /** 获取状态栏高度*/
    fun getStatusBarHeight(fragment: Fragment): Int = ImmersionBar.getStatusBarHeight(fragment)


    fun obtainTitleBar(group: View?): TitleBar? {
        if (group!= null && group is ViewGroup) {
            for (i in 0 until group.childCount) {
                val view = group.getChildAt(i)
                if (view is TitleBar) {
                    return view
                } else if (view is ViewGroup) {
                    val titleBar = obtainTitleBar(view)
                    if (titleBar != null) {
                        return titleBar
                    }
                }
            }
        }
        return null
    }


}