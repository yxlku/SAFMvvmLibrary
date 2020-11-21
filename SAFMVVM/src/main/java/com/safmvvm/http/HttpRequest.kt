package com.safmvvm.http

import androidx.collection.ArrayMap
import com.safframework.log.LogLevel
import com.safmvvm.http.interceptor.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 *
 * 目的1：没网的时候，尝试读取缓存，避免界面空白，只需要addInterceptor和cache即可（已实现）
 * 目的2：有网的时候，总是读取网络上最新的，或者设置一定的超时时间，比如10秒内有多个同一请求，则都从缓存中获取（没实现）
 * 目的3：不同的接口，不同的缓存策略（？）
 *
 */
object HttpRequest {
    // 缓存 service
    private val mServiceMap = ArrayMap<String, Any>()

    /**
     * 请求超时时间，秒为单位
     */
    var mDefaultTimeout = 10

    /**
     * 如果有不同的 baseURL，那么可以相同 baseURL 的接口都放在一个 Service 钟，通过此方法来获取
     */
    fun <T> getService(cls: Class<T>, host: String, vararg interceptors: Interceptor?): T {
        var name = cls.name

        var obj = mServiceMap[name]
        if (obj == null) {
            //
        }
//        return null
    }

    /**
     * 初始化OkHttpClient
     */
    fun initOkHttpClient(vararg interceptors: Interceptor): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
        //超时时间
        okHttpClientBuilder.connectTimeout(mDefaultTimeout.toLong(), TimeUnit.SECONDS)

        //添加从外部传来的拦截器
        interceptors.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        //日志拦截
        okHttpClientBuilder.addInterceptor(
            LoggingInterceptor.Builder()
                .logLevel(LogLevel.INFO)
                .build()
        )

        return okHttpClientBuilder.build()
    }


}