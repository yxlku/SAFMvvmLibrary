package com.longpc.testapplication

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class DebugInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var reqeust: Request = chain.request()
        var response: Response = chain.proceed(reqeust)
        return response
    }
}