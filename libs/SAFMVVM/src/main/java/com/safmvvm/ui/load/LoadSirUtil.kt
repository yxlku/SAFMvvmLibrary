package com.safmvvm.ui.load

import com.safmvvm.app.globalconfig.GlobalConfig
import com.zy.multistatepage.MultiStateConfig
import com.zy.multistatepage.MultiStatePage

object LoadSirUtil {
    /**
     * 初始化页面状态布局
     */
    fun init() {
        //基础配置
        val config = MultiStateConfig.Builder()
            /** 动画持续时间 默认500毫秒*/
            .alphaDuration(GlobalConfig.Loading.LOADING_ALPHA_DURATION)
            .build()
        MultiStatePage.config(config)
    }
}