package com.longpc.testapplication

import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig

class AppApplication: BaseApp() {

    override fun onMainPorcessInitBefore() {

    }
    /**
     * 主进程初始化
     */
    override fun onMainProcessInit() {
        GlobalConfig.Cache.gIsSaveLogToFile = true
        GlobalConfig.Request.SUCCESS_CODE = "0"
        GlobalConfig.Request.gBaseUrl = "https://www.wanandroid.com/"

    }
}