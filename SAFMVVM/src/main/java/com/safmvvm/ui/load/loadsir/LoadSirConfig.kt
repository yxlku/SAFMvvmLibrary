package com.safmvvm.ui.load.loadsir

import com.kingja.loadsir.core.LoadSir
import com.safmvvm.ui.load.ILoad
import com.safmvvm.ui.load.loadsir.callback.DefaultEmptyCallback
import com.safmvvm.ui.load.loadsir.callback.DefaultErrorCallback
import com.safmvvm.ui.load.loadsir.callback.DefaultLoadingCallback

object LoadSirConfig: ILoad {
    /**
     * 获取 LoadSir 的目标，通常是 Activity，或者是某个 view，LoadSir 的页面会挂在该 view 上
     */
    fun getLoadSirTarget(): Any? = null

    override fun initLoad() {
        LoadSir.beginBuilder()
            .addCallback(DefaultLoadingCallback())//等待效果
            .addCallback(DefaultEmptyCallback()) //数据为空
            .addCallback(DefaultErrorCallback()) //错误页面
            .setDefaultCallback(DefaultLoadingCallback::class.java)
            .commit()
    }

    override fun showLoad() {
    }

    override fun dissLoad() {
    }

    override fun reLoad() {
    }

    override fun destroyLoad() {
    }

}