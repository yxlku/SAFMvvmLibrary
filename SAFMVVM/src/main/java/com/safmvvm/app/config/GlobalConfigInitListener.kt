package com.safmvvm.app.config

import androidx.collection.ArrayMap
import okhttp3.Interceptor

/**
 * 初始化接口，配合一些库需要通过自定义方法来自定义初始化
 */
interface GlobalConfigInitListener {

    /**
     * 网络请求头信息
     */
    fun initHeader(): ArrayMap<String, String>

    /**
     * 动态添加拦截器
     */
    fun initInterceptor(): ArrayList<Interceptor>

    /**
     * 全局异常捕获处理
     */
    fun initCrashHandlerDeal(thread: Thread?, ex: Throwable?)
}