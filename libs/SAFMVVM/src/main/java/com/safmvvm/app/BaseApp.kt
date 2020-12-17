package com.safmvvm.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.jaredrummler.cyanea.Cyanea
import com.jiang.awesomedownloader.downloader.AwesomeDownloader
import com.safmvvm.app.globalconfig.GlobalConfigFun
import com.safmvvm.component.RouterUtil
import com.safmvvm.ui.theme.ThemeUtil
import com.safmvvm.utils.AppUtil
import com.safmvvm.utils.LogUtil

/**
 * 所有子Module都要继承此BaseApp
 * 所有统一初始化的工具都放到此处
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        onMainPorcessInitBefore()

        initApp(this)

        val processName = AppUtil.currentProcessName
        if (processName == packageName) {
            //当前进程
            initResource(this)
            //主进程操作
            onMainProcessInit()
        } else {
            //其他进程 ?.let 相当于 != null
            processName?.let {
                onOtherProcessInit(it)
            }
        }
    }

    /** initApp方法前调用，比如日志开启和DebugView*/
    open fun onMainPorcessInitBefore() {}

    /**
     * 通常来说应用只有一个进程，进程名称是当前的包名，你需要针对这个进程做一些初始化。
     * 如果你引入了第三方服务，比如地图，推送什么的，很可能对方是开了个额外的进程在跑的，
     * 这个时候就没必要初始化你的资源了，因为它根本用不上你的。
     *
     * 如果你自己开了多进程，那么就复写 [onCreate] 自己判断要怎么初始化资源吧
     */
    open fun onMainProcessInit() {}

    /**
     * 其他进程初始化，[processName] 进程名
     */
    open fun onOtherProcessInit(processName: String) {}

    companion object {
        private lateinit var app: Application

        @JvmStatic
        fun initApp(app: Application) {
            Companion.app = app
            //初始化路由框架
            RouterUtil.initARouter(app)
            //主题初始化
            ThemeUtil.init(app)
            //日志初始化
            LogUtil.initLog()
            //等待布局初始化
            GlobalConfigFun.initPageStateConfig()
            //开启全局日常捕获
            CrashHandlerUtil.init()
            //初始化下载器
            AwesomeDownloader.init(app)
        }

        private fun initResource(app: Application) {
            // 监听所有 Activity 的创建和销毁
            app.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    AppActivityManager.add(activity)
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityResumed(activity: Activity) {
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                    AppActivityManager.remove(activity)
                }
            })
        }

        @JvmStatic
        fun getInstance(): Application {
            return app
        }
    }


}