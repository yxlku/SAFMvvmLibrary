package com.test.common

import android.content.Context
import android.view.Gravity
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.safmvvm.app.AppInitializer
import com.safmvvm.app.GlobalConfigCreator
import com.safmvvm.db.MigrationManager
import com.safmvvm.ui.autosize.AutoSizeUtil
import com.test.common.base.ProjectConfigListener
import com.test.common.loading.CusLoadStatePage

class AInitializer: AppInitializer() {

    override fun initOther(context: Context) {
        super.initOther(context)

        GlobalConfigCreator()
            //是否开启日志功能
            .logIsOpen(BuildConfig.DEBUG)
            //统一设计稿尺寸
            .autoSizeDesignSize(375F, 812F)
            //开启路由器
            .appIsOpenRouter(true)
            //支持侧滑关闭功能
            .appIsOpenSwipeback(true)
            //关闭-默认打开、关闭页面动画
            .animIsOpen(false)
            //请求host
            .requestBaseUrl("http://192.168.10.11/DETI-System/")
            //请求成功code
            .requestSuccessCode("00")
            //应急版本更新地址 - 防止app下载不好用或者不适配机型导致无法应用内更新跳转到的页面
            .updateNoApkUrl("https://app.mi.com/")
            //初始化函数配置
            .setGlobalConfigInitListener(ProjectConfigListener())
            //Toast自定义布局
            .toastCustomLayoutId(R.layout.base_toast_layout)
            //自定义Toast文字控件Id
            .toastCustomMsgId(R.id.toast_msg_id)
            //自定义Toast显示位置
            .toastCustomToastGravity(Gravity.CENTER)
            //标题栏通用返回按钮
            .titleBarBackIcon(R.mipmap.base_title_back)
            //状态栏文字黑色
            .appIsStatusBarIsDark(true)

            .loadingLayoutText("加载中")
            .loadingLayoutId(R.layout.saf_loading_layout)
            .pageStateLoading(CusLoadStatePage::class.java)

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
    }


}