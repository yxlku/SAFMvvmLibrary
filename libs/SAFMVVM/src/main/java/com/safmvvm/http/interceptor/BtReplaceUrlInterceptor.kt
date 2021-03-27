package com.safmvvm.http.interceptor

import androidx.core.net.toUri
import com.safmvvm.app.globalconfig.GlobalConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 专门针对变态url的处理，比如：一个项目一个baseUrl，结果分模块，每个模块对应不同端口号，这时候，一个BaseUrl跟不不能解决问题
 * 我们总不能通过常量String拼接的方式来做，所以统一拦截处理，哪天改了，直接不用这个方法就行
 */
class BtReplaceUrlInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request = chain.request()
        val oldHttpUrl: HttpUrl = request.url
        var newHttpUrl: HttpUrl = oldHttpUrl
        //公共接口对参数进行处理，如果不处理则直接用单独请求方式
        GlobalConfig.App.gGlobalConfigInitListener?.let {
            //防止将原数据清空
            val newUrl: String = GlobalConfig.App.gGlobalConfigInitListener?.requestBtReplaceUrl(oldHttpUrl.toString()) ?: oldHttpUrl.toString()
            newUrl.toHttpUrlOrNull()?.apply {
                newHttpUrl = this
            }
        }
        val newBuilder: HttpUrl.Builder = newHttpUrl.newBuilder()
        //构建新的Requst
        request = request.newBuilder()
            .url(newBuilder.build())
            .build()
        return chain.proceed(request)
    }
}