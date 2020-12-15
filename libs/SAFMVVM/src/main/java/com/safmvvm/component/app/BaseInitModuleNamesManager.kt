package com.safmvvm.component.app

/**
 * CommonBase中使用即可
 *
 * 子Module初始化Module映射类基类
 */
interface BaseInitModuleNamesManager {

    /**
     * 基类中定义的初始化子Module类映射路径
     */
    fun configNames(): ArrayList<String>

}