package com.safmvvm.http.interceptor

import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.utils.EncodeUtil
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Get请求参数处理
 */
class GetDataInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var method: String = request.method

        var params: HashMap<String, String?> = HashMap()

        if (method == "GET") {
            var url: HttpUrl = request.url
            for (i in 0 until url.querySize) {
                //取出url中？后的参数
                val key = url.queryParameterName(i)
                val value = url.queryParameterValue(i)
                params.put(key, value)
            }
            //如果没有注册监听则直接返回数据源
            var dealParams: HashMap<String, String?> = params
            GlobalConfig.App.gGlobalConfigInitListener?.let {
                var newParam = GlobalConfig.App.gGlobalConfigInitListener?.requestDataGetDeal(params)
                newParam?.let {
                    dealParams = newParam
                }
            }

            var newBuilder: HttpUrl.Builder = url.newBuilder()
            dealParams.forEach { (key, value) ->
                newBuilder.setQueryParameter(EncodeUtil.encode(key), EncodeUtil.encode(value))
            }

            //构建新的Requst
            request = request.newBuilder()
                .url(newBuilder.build())
                .build()
        }

        return chain.proceed(request)
    }
}