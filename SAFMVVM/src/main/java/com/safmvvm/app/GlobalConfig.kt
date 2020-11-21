package com.safmvvm.app

import android.os.Build
import com.safmvvm.BuildConfig

/**
 * 全局配置
 * 开关默认都关闭，在子Module中初始化，自行开启即可
 */
object GlobalConfig {

    var appName = "SAFMVVM"




    /**  是否需要管理 Activity 堆栈 */
    var gIsNeedActivityManager = false

    /** 是否保存日志到文件中*/
    var gIsSaveLogToFile = false
    /** 日志保存文件位置*/
    var gLogSaveFileDir = ""
    /** 日志基础名字*/
    var gLogFileBaseName = ""
    /** 是否开启logDebugView */
    var gIsShowLogDebugView = false

}