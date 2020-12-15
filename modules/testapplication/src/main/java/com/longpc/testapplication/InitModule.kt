package com.longpc.testapplication

import android.app.Application
import com.safmvvm.component.app.IModuleInit
import com.safmvvm.utils.LogUtil

class InitModule: IModuleInit {
    override fun onInitAhead(app: Application) {
        LogUtil.d("我在testapplication中初始化了！！")
    }

    override fun onInitLow(app: Application) {
    }
}