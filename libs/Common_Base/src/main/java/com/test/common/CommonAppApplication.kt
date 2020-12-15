package com.test.common

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alibaba.android.arouter.launcher.ARouter
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfigCreator
import com.safmvvm.component.app.ComponentBaseApp
import com.safmvvm.db.MigrationManager
import com.safmvvm.utils.LogUtil
import com.test.common.InitModuleNamesManager

open class CommonAppApplication: ComponentBaseApp() {

    /**
     * 获取自定义配置的子Module初始化类
     */
    override fun configModuleNames(): ArrayList<String> {
        return InitModuleNamesManager.configNames()
    }

    override fun onMainPorcessInitBefore() {
        super.onMainPorcessInitBefore()
    }

    override fun onMainProcessInit() {
        super.onMainProcessInit()

        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化


        GlobalConfigCreator()
            .requestSuccessCode("0")
            .requestBaseUrl("https://www.wanandroid.com/")
//            .requestBaseUrl("https://api.apiopen.top/")
            .updateNoApkUrl("https://app.mi.com/")
            .logIsOpen(BuildConfig.DEBUG)
            .loadingLayoutText("我在App中初始化了")
//            .pageStateLoading(CusLoadStatePage::class.java)
//            .setGlobalConfigInitListener(ProjectConfigListener())       //初始化方法
            .pageStateDefErrorMsg("Error了，肯定是手机有问题！代码没问题")
            .pageStateDefFailMsg("Fail了，肯定是手机有问题！代码没问题")
            .dbName("db_test") //数据库名称
            .dbConfigMigrationManager(
                MigrationManager
                    .addMigration(object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE table_te ADD COLUMN sex TEXT")
                        }
                    }).addMigration(object : Migration(2, 3) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE table_te ADD COLUMN other TEXT")
                        }
                    })
            )
            .build()
    }
}