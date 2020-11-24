package com.safmvvm.app

import com.safmvvm.utils.FileUtil


/**
 * 全局配置
 * 开关默认都关闭，在子Module中初始化，自行开启即可
 */
object GlobalConfig {

    const val appName = "SAFMVVM"

    /**
     * 是否需要动态修改 BaseURL，如果需要，请设置为 true，并在合适的位置调用：[com.imyyq.mvvm.http.HttpRequest.multiClickToChangeBaseUrl]
     */
    var gIsNeedChangeBaseUrl = false

    /**  是否需要管理 Activity 堆栈 */
    var gIsNeedActivityManager = false

    /** 是否保存日志到文件中*/
    var gIsSaveLogToFile = false
    /** 日志保存文件位置 取得App的log目录：/sdcard/包名/Log，没有sd卡则是/data/data/包名/files/Log*/
    var gLogSaveFileDir = FileUtil.appLogDir
    /** 日志基础名字*/
    var gLogFileBaseName = "Log_"


}