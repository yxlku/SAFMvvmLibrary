package com.safmvvm.http.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.safmvvm.app.globalconfig.GlobalConfig
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * 请求Json解析
 */
class SAFGsonConverterFactory(
    val gson: Gson
): Converter.Factory() {

    companion object {
        fun create(): SAFGsonConverterFactory{
            return create(Gson())
        }
        fun create(gson: Gson): SAFGsonConverterFactory{
            if (gson == null) {
                //自定义处理
                GlobalConfig.App.gGlobalConfigInitListener?.dateParseException(false, "Gson为空", NullPointerException())
            }
            return SAFGsonConverterFactory(gson)
        }
    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<Annotation?>?,
        methodAnnotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): SAFRequestConverter<*> {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return SAFRequestConverter(gson, adapter)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return SAFResponseConverter(gson, adapter)
    }


}