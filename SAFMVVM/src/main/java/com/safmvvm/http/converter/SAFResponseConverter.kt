package com.safmvvm.http.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.safmvvm.app.globalconfig.GlobalConfig
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Converter
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

/**
 * 响应统一处理
 * 1、返回数据统一解密
 * 2、抛出统一接口方法解密
 */
class SAFResponseConverter<T>(
    var gson: Gson,
    var adapter: TypeAdapter<T>
): Converter<ResponseBody, T> {
    override fun convert(responseBody: ResponseBody): T {
        //返回数据操作
        //返回请求的数据 -- 加密
        var responseBodyStr: String = resolveServerResponseData(responseBody)
        //解密后的数据 -- 解密
        var decryptedData: String? = responseBodyStr
        //返回结果公共处理方法
        GlobalConfig.App.gGlobalConfigInitListener?.let {
            //防止将原数据清空
            responseBodyStr?.let {
                decryptedData = GlobalConfig.App.gGlobalConfigInitListener?.responseDataDeal(responseBodyStr)
            }
        }
        return adapter.fromJson(decryptedData)
    }

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