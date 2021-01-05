package com.example.moduletesta

import android.app.Application
import com.safmvvm.component.app.IModuleInit
import com.safmvvm.utils.LogUtil
import com.safmvvm.ui.toast.ToastUtil

class InitModule: IModuleInit {
    override fun onInitAhead(app: Application) {
        LogUtil.d("我在ModuleTestA中初始化了")
    }

    override fun onInitLow(app: Application) {
    }
}