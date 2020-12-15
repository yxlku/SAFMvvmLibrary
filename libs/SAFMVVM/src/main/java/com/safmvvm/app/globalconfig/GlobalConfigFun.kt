package com.safmvvm.app.globalconfig

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.safmvvm.app.BaseApp
import com.zy.multistatepage.MultiStateConfig
import com.zy.multistatepage.MultiStatePage


/**
 * 通过方法配置 全局设置
 */
internal object GlobalConfigFun {

    /**
     * 初始化页面状态布局
     */
    fun initPageStateConfig(
        /** 动画持续时间 默认500毫秒*/
        alphaDuration: Long = GlobalConfig.Loading.LOADING_ALPHA_DURATION,
    ) {
        //基础配置
        val config = MultiStateConfig.Builder()
            .alphaDuration(alphaDuration)
            .build()
        MultiStatePage.config(config)
    }

}