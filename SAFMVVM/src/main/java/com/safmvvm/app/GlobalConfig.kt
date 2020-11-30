package com.safmvvm.app

import com.safmvvm.utils.FileUtil


/**
 * 全局配置
 * 开关默认都关闭，在子Module中初始化，自行开启即可
 */
object GlobalConfig {

    const val appName = "SAFMVVM"

    object App{
        /**  是否需要管理 Activity 堆栈 */
        var gIsNeedActivityManager = false
    }

    /**
     * 请求可配置项
     */
    object Request{
        /** baseUrl*/
        var gBaseUrl: String = ""
        /** 请求等待显示信息, 可以在子Module中配置全局，也可以在实现中，具体实现方法中修改对应的内容*/
        var gLodingMsg: String = "正在加载中，请稍候。。。"
        /** 必须设置*/
        var SUCCESS_CODE: String = ""
        /** 超时时间 */
        var DEFAULT_TIMEOUT: Long = 20
        /** 请求超时时间，秒为单位 */
        var DEFAULT_TIME_OUT = 10
        /**
         * 是否需要动态修改 BaseURL，如果需要，请设置为 true，并在合适的位置调用：[com.imyyq.mvvm.http.HttpRequest.multiClickToChangeBaseUrl]
         */
        var gIsNeedChangeBaseUrl = false
    }

    object Log{
        /** 全局日志tag，默认为app的名字*/
        var gLogTag = appName
        /** 是否开启Log*/
        var gIsOpenLog = false;
        /** 是否保存日志到文件中*/
        var gIsSaveLogToFile = false
        /** 日志保存文件位置 取得App的log目录：/sdcard/包名/Log，没有sd卡则是/data/data/包名/files/Log*/
        var gLogSaveFileDir = FileUtil.appLogDir
        /** 日志基础名字*/
        var gLogFileBaseName = "Log_"
    }

    /**
     * 缓存
     */
    object Cache{

    }

}