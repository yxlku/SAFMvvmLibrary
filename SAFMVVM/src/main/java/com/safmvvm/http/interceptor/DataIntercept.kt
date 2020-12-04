package com.safmvvm.http.interceptor

import com.safmvvm.utils.LogUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.BufferedSource
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

/**
 * 数据拦截器
 */
class DataIntercept: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var response = chain.proceed(request)
        var responseBody = response.body

        try {
            //TODO 解密操作
            //返回请求的数据
            var responseBodyStr: String = resolveServerResponseData(responseBody)
            //解密后的数据
            var decryptedData: String = responseBodyStr
            return response.newBuilder().body(decryptedData.toResponseBody("application/json;charset=utf-8".toMediaType())).build();
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtil.exception(ex = e)
        }
        return response
    }

//    fun resolveClientRequestData(): String{
//
//    }

    /**
     * 解析服务器返回内容 - 这里可能是加密的
     */
    fun resolveServerResponseData(
        responseBody: ResponseBody?
    ): String {
        responseBody?.let {
            var source: BufferedSource = responseBody.source()
            source.request(Long.MAX_VALUE)

            var buffer = source.buffer
            var charset = Charset.forName("UTF-8")
            var contentType = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(Charset.forName("UTF-8"))
                } catch (e: UnsupportedCharsetException) {
                    e.printStackTrace()
                }
            }
            //接口返回的加密数据
            return buffer.clone().readString(charset);
        }
        //responseBody 为空直接返回""
        return ""
    }


}