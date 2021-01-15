package com.safmvvm.ui.autosize

import android.util.TypedValue
import android.widget.TextView
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.ui.titlebar.initializer.BaseBarInitializer
import com.safmvvm.utils.LogUtil
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.external.ExternalAdaptInfo
import me.jessyan.autosize.unit.Subunits

fun TextView.setTextSizeAuto(size: Float){
    this.setTextSize(TypedValue.COMPLEX_UNIT_MM, size)
}

/**
 * 屏幕适配
 */
object AutoSizeUtil {

    /**
     * 初始化
     */
    fun init() {
        if (!GlobalConfig.AutoSize.gIsOpenAutoSize) {
            LogUtil.w("没有开启自动适配功能，全局需要使用dp作为单位！！")
            return
        }
        var manager = AutoSizeConfig
            .getInstance()
            .unitsManager
        GlobalConfig.AutoSize.gDesignSize.let {
            manager.setDesignSize(it.first, it.second)
        }
        manager.setSupportDP(false)
            .setSupportSP(false)
            .supportSubunits = Subunits.MM

        //自定义过滤问题
        customAdaptForExternal()
    }

    /**
     * 自定义控件中页面或View过滤，使用不同尺寸适配
     */
    fun customAdaptForExternal(){
        var manager = AutoSizeConfig.getInstance().externalAdaptManager
        LogUtil.d("size: ${GlobalConfig.AutoSize.gCustomAdapter?.size}")
        GlobalConfig.AutoSize.gCustomAdapter?.forEach {
            manager.addExternalAdaptInfoOfActivity(it.first, ExternalAdaptInfo(it.second, it.third))
        }
    }

}