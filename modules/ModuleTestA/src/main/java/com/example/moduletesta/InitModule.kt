package com.example.moduletesta

import android.app.Application
import com.safmvvm.component.app.IModuleInit
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil

class InitModule: IModuleInit {
    override fun onInitAhead(app: Application) {
        LogUtil.d("我在ModuleTestA中初始化了")
        ToastUtil.showShortToast("我是MainTestAActivity,我初始化了！！！onInitAhead")
    }

    override fun onInitLow(app: Application) {
    }
}