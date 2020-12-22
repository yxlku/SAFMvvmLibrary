package com.test.app

import com.safmvvm.ui.autosize.AutoSizeUtil
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.ui.titlebar.initializer.BaseBarInitializer
import com.safmvvm.utils.LogUtil
import com.test.common.CommonAppApplication
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.external.ExternalAdaptInfo

class AppApplication: CommonAppApplication() {


    override fun onMainProcessInit() {
        super.onMainProcessInit()
        AutoSizeUtil.init()
        LogUtil.d("我在壳App中初始化了！")
    }
}