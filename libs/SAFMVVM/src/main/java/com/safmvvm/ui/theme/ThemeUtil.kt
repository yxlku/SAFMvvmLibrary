package com.safmvvm.ui.theme

import android.app.Application


/**
 * 主题工具
 */
object ThemeUtil {

    /**
     * 主题初始化，放在Application中
     */
    fun init(app: Application){
//        //主题初始化
//        Cyanea.init(app, app.resources)
//        //日志开关
//        Cyanea.loggingEnabled = GlobalConfig.Log.gIsOpenLog
    }

//    /**
//     * 获取主题样式
//     */
//    fun getTheme(
//        /** 选择位置*/
//        pos: Int = 0,
//        /** 主题存放路径*/
//        themesJsonAssetPath: String = "themes/SAF_themes.json"
//    ): CyaneaTheme?{
//        try {
//            val themes = CyaneaTheme.from(BaseApp.getInstance().assets, themesJsonAssetPath)
//            return themes[pos]
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }

//    /**
//     * 改变主题，只能传入Activity
//     *
//     * 改变后主题会一直改变
//     */
//    fun applyTheme(
//        activity: Activity,
//        cyanea: Cyanea,
//        cyaneaTheme: CyaneaTheme,
//        delay: Long = 0,
//        smooth: Boolean = false
//    ){
//        cyaneaTheme.apply(cyanea).recreate(activity, delay, smooth)
//    }
}