package com.longpc.testapplication

import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig
import com.safmvvm.utils.LogUtil

class AppApplication: BaseApp() {

    override fun onMainPorcessInitBefore() {

    }
    /**
     * 主进程初始化
     */
    override fun onMainProcessInit() {
        GlobalConfig.gIsSaveLogToFile = true

    }
}