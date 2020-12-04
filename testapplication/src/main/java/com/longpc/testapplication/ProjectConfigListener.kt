package com.longpc.testapplication

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.safmvvm.app.config.GlobalConfigInitListener
import com.safmvvm.utils.ToastUtil
import okhttp3.Interceptor

class ProjectConfigListener: GlobalConfigInitListener {

    /**
     * 项目中用到的头信息
     */
    override fun initHeader(): ArrayMap<String, String> {
        var headers: ArrayMap<String, String> = arrayMapOf()
        headers.put("test1", "我是测试Header1")
        headers.put("test2", "我是测试Header2")
        headers.put("test2", "我是测试Header3")
        headers.put("User-Agent", "CHrome111")
        return headers
    }

    /**
     * 项目中用到的拦截器
     */
    override fun initInterceptor(): ArrayList<Interceptor> {
        var interceptors: ArrayList<Interceptor> = arrayListOf()
        if (BuildConfig.DEBUG) {
            interceptors.add(DebugInterceptor())
        }
        return interceptors
    }

    /**
     * 全局异常捕获处理
     */
    override fun initCrashHandlerDeal(thread: Thread?, ex: Throwable?) {
        ToastUtil.showShortToast("我擦，我崩溃了！！错误原因：" + ex?.message )
    }
}