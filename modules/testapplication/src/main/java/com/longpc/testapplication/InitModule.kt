package com.longpc.testapplication

import android.app.Application
import com.safmvvm.app.GlobalConfigCreator
import com.safmvvm.component.app.IModuleInit
import com.safmvvm.utils.LogUtil
import me.jessyan.autosize.AutoSizeConfig

class InitModule: IModuleInit {
    override fun onInitAhead(app: Application) {
        LogUtil.d("我在testapplication中初始化了！！")

        GlobalConfigCreator().autoSizeCustomAdapter(ArrayList<Triple<Class<*>, Boolean, Float>>().apply {
            add(Triple(MainActivity::class.java, true, 720f))
        })
    }

    override fun onInitLow(app: Application) {
    }
}