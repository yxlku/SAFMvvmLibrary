package com.safmvvm.component.app

import com.safmvvm.app.BaseApp
import com.safmvvm.utils.ReflectUtil.instance

/**
 * 组件化"壳App"继承此Application
 */
abstract class ComponentBaseApp: BaseApp() {

    /**
     * 子Module中的初始化映射类路径
     */
    abstract fun configModuleNames(): ArrayList<String>

    override fun onCreate() {
        super.onCreate()
        //初始化组件(靠前)
        ModuleLifecycleConfig.instance.initModuleAhead(this, configModuleNames())

        //初始化组件(靠后)
        ModuleLifecycleConfig.instance.initModuleLow(this, configModuleNames())
    }


}