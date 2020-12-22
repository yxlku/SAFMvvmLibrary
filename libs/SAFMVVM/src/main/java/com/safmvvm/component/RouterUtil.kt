package com.safmvvm.component

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.safmvvm.app.globalconfig.GlobalConfig


/**
 * Router初始化统一工具
 */
object RouterUtil {

    /**
     * 初始化路由
     */
    fun initARouter(app: Application) {
        //初始化阿里路由框架
        if (GlobalConfig.App.gIsOpenArouter) {
            if (GlobalConfig.Log.gIsOpenLog) {
                ARouter.openLog() // 打印日志
                ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            }
            ARouter.init(app) // 尽可能早，推荐在Application中初始化
        }
    }

    /**
     * 初始化注入，放到BaseActivity或BaseFragment即可
     */
    fun inject(activityOrFragmentClass: Any) {
        ARouter.getInstance().inject(activityOrFragmentClass)
    }

    fun startActivity(
        path: String,
        block: (postcard: Postcard) -> Postcard
    ) {
        ARouter.getInstance().build(path).run {
            block(this)
        }.navigation()
    }


}