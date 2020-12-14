package com.safmvvm.http.interceptor

import androidx.collection.ArrayMap
import com.safmvvm.utils.EncodeUtil
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
        return chain.proceed(requestBuilder.build())

    }
}