package com.safmvvm.http.interceptor

import androidx.collection.ArrayMap
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.utils.encrypt.encode.EncodeUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 头信息拦截
 */
class HeaderInterceptor constructor(var mHeaders: ArrayMap<String, String>): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var requestBuilder = chain.request().newBuilder()

        for ((key, value) in mHeaders) {
            requestBuilder.addHeader(EncodeUtil.encode(key), EncodeUtil.encode(value))
        }
        GlobalConfig.App.gGlobalConfigInitListener?.initHeaderDynamic()?.forEach{
            it.value?.apply {
                requestBuilder.addHeader(EncodeUtil.encode(it.key), EncodeUtil.encode(this))
            }
        }
        return chain.proceed(requestBuilder.build())

    }
}