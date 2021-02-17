package com.safmvvm.app

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.jiang.awesomedownloader.downloader.AwesomeDownloader
import com.safmvvm.BuildConfig
import com.safmvvm.component.RouterUtil
import com.safmvvm.ui.autosize.AutoSizeUtil
import com.safmvvm.ui.load.LoadSirUtil
import com.safmvvm.ui.swipebacklayout.SwipebacklayoutUtil
import com.safmvvm.ui.theme.ThemeUtil
import com.safmvvm.utils.AppUtil
import com.safmvvm.utils.KVCacheUtil
import com.safmvvm.utils.LogUtil
import com.tencent.mmkv.MMKV

open class AppInitializer : Initializer<Unit> {

    open fun initOther(context: Context){}

    override fun create(context: Context) {

        initApp(context)

        initOther(context)

    }

    private fun initApp(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
        }
        ARouter.init(context.applicationContext as Application)

        var app = context as Application
        //屏幕适配
        AutoSizeUtil.init()
        //初始化路由框架
        RouterUtil.initARouter(app)
        //主题初始化
        ThemeUtil.init(app)
        //滑动返回
        SwipebacklayoutUtil.init(app)
        //日志初始化
        LogUtil.initLog()
        //等待布局初始化
        LoadSirUtil.init()
        //开启全局日常捕获
        CrashHandlerUtil.init()
        //初始化下载器
        AwesomeDownloader.init(app)
        //缓存开启
        KVCacheUtil.init(app)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}