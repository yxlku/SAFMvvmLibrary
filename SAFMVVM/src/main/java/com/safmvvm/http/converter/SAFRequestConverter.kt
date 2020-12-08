package com.safmvvm.http.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.safmvvm.app.config.GlobalConfig
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Converter
import java.lang.RuntimeException

/**
 * 请求统一处理
 * 1、统一参数处理：
 *      1.1、GET请求参数封装
 *      1.2、Post请求参数封装
 *          1.2.1、Body
 *          1.2.2、Form
 *      1.3、图片、文件不添加
 * 2、参数统一加密
 * 3、抛出公开方法
 *
 * 以下是使用Body（）方法请求，由于@Field的字段注解方式不会调用convert方法，所以才会在设置OkHttpClient的时候添加自定义Interceptor,使用Converter：
 */
class SAFRequestConverter<T>(
    var gson: Gson,
    var adapter: TypeAdapter<T>
): Converter<T, RequestBody> {

    private val MEDIA_TYPE: MediaType? = "application/json; charset=UTF-8".toMediaTypeOrNull()
    override fun convert(value: T): RequestBody? {
        //value类型和传入参数有关系
        //明文数据
        var dataPlaintext: String = JsonUtil.toJson(value!!)
        //在此处做加密请求 -- 如果子Module中没有修改这个方法或返回null则直接用明文来处理
        var dataDealResult:String = dataPlaintext

        //公共接口对参数进行处理，如果不处理则直接用单独请求方式
        GlobalConfig.App.gGlobalConfigInitListener?.let {
            //防止将原数据清空
            var dealData: String? = GlobalConfig.App.gGlobalConfigInitListener?.requestDataBodyDeal(dataPlaintext)
            dealData?.let {
                dataDealResult  = dealData
            }
        }

        //处理后的数据
        //不能对数据做Json判断，加密后不一定使用Json格式
        return dataDealResult.toRequestBody(MEDIA_TYPE)
    }

}