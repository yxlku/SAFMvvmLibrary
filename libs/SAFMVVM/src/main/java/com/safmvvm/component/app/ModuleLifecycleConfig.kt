package com.safmvvm.component.app

import android.app.Application

/**
 * 子Module的初始化类加载器，通常在框架中调用即可
 */
class ModuleLifecycleConfig {

    companion object{
        val instance: ModuleLifecycleConfig by lazy{ ModuleLifecycleConfig()}
    }

    /**
     * 初始化组件-靠前
     */
    fun initModuleAhead(app: Application, initModuleNames: List<String>){
        initModuleNames.forEach {
            try {
                var clazz: Class<*> = Class.forName(it)
                var moduleInit: IModuleInit = clazz.newInstance() as IModuleInit
                //调用初始化方法
                moduleInit.onInitAhead(app)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 初始化组件-靠后
     */
    fun initModuleLow(app: Application, initModuleNames: List<String>){
        initModuleNames.forEach {
            try {
                var clazz: Class<*> = Class.forName(it)
                var moduleInit: IModuleInit = clazz.newInstance() as IModuleInit
                ///调用初始化方法
                moduleInit.onInitLow(app)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

}