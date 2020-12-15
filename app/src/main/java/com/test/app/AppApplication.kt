package com.test.app

import com.safmvvm.app.BaseApp
import com.safmvvm.component.app.ComponentBaseApp
import com.safmvvm.utils.LogUtil
import com.test.common.CommonAppApplication
import com.test.common.InitModuleNamesManager

class AppApplication: CommonAppApplication() {


    override fun onMainPorcessInitBefore() {
        super.onMainPorcessInitBefore()
    }

    override fun onMainProcessInit() {
        super.onMainProcessInit()

        LogUtil.d("我在壳App中初始化了！")
    }
}