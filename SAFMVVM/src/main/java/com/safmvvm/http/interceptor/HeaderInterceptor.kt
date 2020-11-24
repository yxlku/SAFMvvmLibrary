package com.safmvvm.http.interceptor

import androidx.collection.ArrayMap
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 头信息拦截
 */
class HeaderInterceptor constructor(var mHeaders: ArrayMap<String, String>): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var requestBuilder = chain.request().newBuilder()

        mHeaders.let {
            mHeaders.entries.forEach {
                requestBuilder.addHeader(it.key, it.value).build()
            }
        }

        return chain.proceed(chain.request())

    }
}