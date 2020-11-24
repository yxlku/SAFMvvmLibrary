package com.safmvvm.http.interceptor

import android.content.Context
import com.safmvvm.utils.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 缓存拦截
 * 无网络状态下智能读取缓存的拦截器
 */
class CachInterceptor constructor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkUtil.isConnected(context)) {
            //有网络
            var response  = chain.proceed(request)
            //从缓存读取60秒
            val maxAge = 60
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build()
        }else{
            //无网络走缓存
            request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            var response = chain.proceed(request)
            //设置缓存时间为3天
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build()
        }

    }
}