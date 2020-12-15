package com.safmvvm.component.app

import android.app.Application

/**
 * 子Module中使用
 * 动态配置Application，有需要初始化的组件实现该接口，统一在主app的Application中初始化
 * 所有子Module必须继承此接口（如果需要自定义初始化）
 */
interface IModuleInit {

    /**
     * 初始化优先的
     */
    fun onInitAhead(app: Application)


    /**
     * 初始化靠后的
     */
    fun onInitLow(app: Application)

}