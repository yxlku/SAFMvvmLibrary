package com.longpc.testapplication.base

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.longpc.testapplication.BuildConfig
import com.longpc.testapplication.loading.CusLoadStatePage
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfigCreator
import com.safmvvm.db.MigrationManager

class AppApplication : BaseApp() {

    override fun onMainPorcessInitBefore() {

    }

    /**
     * 主进程初始化
     */
    override fun onMainProcessInit() {
        GlobalConfigCreator()
            .requestSuccessCode("0")
            .requestBaseUrl("https://www.wanandroid.com/")
//            .requestBaseUrl("https://api.apiopen.top/")
            .updateNoApkUrl("https://app.mi.com/")
            .logIsOpen(BuildConfig.DEBUG)
            .loadingLayoutText("我在App中初始化了")
            .pageStateLoading(CusLoadStatePage::class.java)
            .setGlobalConfigInitListener(ProjectConfigListener())       //初始化方法
            .pageStateDefErrorMsg("Error了，肯定是手机有问题！代码没问题")
            .pageStateDefFailMsg("Fail了，肯定是手机有问题！代码没问题")
            .dbName("db_test") //数据库名称
            .dbConfigMigrationManager(
                MigrationManager
                    .addMigration(object: Migration(1, 2){
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE table_te ADD COLUMN sex TEXT")
                        }
                    }).addMigration(object: Migration(2, 3){
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE table_te ADD COLUMN other TEXT")
                        }
                    })
            )
            .build()
    }
}