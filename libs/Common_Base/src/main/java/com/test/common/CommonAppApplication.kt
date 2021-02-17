package com.test.common

import android.view.Gravity
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.safmvvm.app.GlobalConfigCreator
import com.safmvvm.component.app.ComponentBaseApp
import com.safmvvm.db.MigrationManager
import com.safmvvm.ui.autosize.AutoSizeUtil
import com.test.common.base.ProjectConfigListener
import com.test.common.loading.CusLoadStatePage

open class CommonAppApplication : ComponentBaseApp() {

    /**
     * 获取自定义配置的子Module初始化类
     */
    override fun configModuleNames(): ArrayList<String> {
        return InitModuleNamesManager.configNames()
    }

}