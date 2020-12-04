package com.longpc.testapplication

import com.longpc.testapplication.loading.CusLoadStatePage
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig
import com.zy.multistatepage.state.EmptyState

class AppApplication: BaseApp() {

    override fun onMainPorcessInitBefore() {

    }
    /**
     * 主进程初始化
     */
    override fun onMainProcessInit() {

        GlobalConfig.Request.SUCCESS_CODE = "0"
        GlobalConfig.Request.gBaseUrl = "https://www.wanandroid.com/"
        GlobalConfig.Log.gIsOpenLog = true
        GlobalConfig.Log.gIsSaveLogToFile = true

//        GlobalConfig.Loading.LOADING_TEXT = "我在App中初始化了"
        GlobalConfig.initMultiStateConfig(pageStateLoading = CusLoadStatePage::class.java)
    }
}