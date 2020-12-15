package com.test.common

import com.safmvvm.component.app.BaseInitModuleNamesManager


/**
 * 子Module初始化类管理器，通过类路径反射初始化，并在Application中加载
 */
object InitModuleNamesManager: BaseInitModuleNamesManager {

    override fun configNames(): ArrayList<String> {
        return arrayListOf<String>(
            "com.example.moduletesta.InitModule",
            "com.longpc.testapplication.InitModule"
        )
    }
}
