package com.longpc.testapplication

import com.longpc.testapplication.loading.CusLoadStatePage
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfigCreateor
import com.safmvvm.ui.load.state.DefaultErrorPageState

class AppApplication: BaseApp() {

    override fun onMainPorcessInitBefore() {

    }
    /**
     * 主进程初始化
     */
    override fun onMainProcessInit() {

        GlobalConfigCreateor()
            .requestSuccessCode("0")
            .requestBaseUrl("https://www.wanandroid.com/")
            .logIsOpen(BuildConfig.DEBUG)
            .loadingLayoutText("我在App中初始化了")
            .pageStateLoading(CusLoadStatePage::class.java)
            .setGlobalConfigInitListener(ProjectConfigListener())       //初始化方法
            .pageStateDefErrorMsg("Error了，肯定是手机有问题！代码没问题")
            .pageStateDefFailMsg("Fail了，肯定是手机有问题！代码没问题")
            .build()


    }
}