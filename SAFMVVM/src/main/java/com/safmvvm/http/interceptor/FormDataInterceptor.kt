package com.safmvvm.http.interceptor

import com.safmvvm.app.config.GlobalConfig
import com.safmvvm.utils.LogUtil
import okhttp3.*

/**
 * Form表单提交时的拦截
 */
class FormDataInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        //作用：将post中的请求参数取出加密，并且替换原来的请求参数；ps：重新添加的参数是Query形式，并未添加到encode中
        //将加密后的data直接使用addEnable请求不成功，可能和后台的请求类型有关系
        val map: HashMap<String, Any> = HashMap()

        //使用@Field添加参数
        if ("POST" == request.method && request.body is FormBody) { //post请求有参数时
            val formBody: FormBody = request.body as FormBody
            val bodyBuilder: FormBody.Builder = FormBody.Builder() //新建一个请求Body
            if (formBody.size > 0) {
                for (i in 0 until formBody.size) { //将post请求的参数依次取出再加密
                    map[formBody.encodedName(i)] = formBody.encodedValue(i)
                }
            }
            //处理参数 -- 如果公共方法不处理，则直接只用，默认参数
            var dealMap: HashMap<String, Any> = map
            GlobalConfig.App.gGlobalConfigInitListener?.let {
                var newMap = GlobalConfig.App.gGlobalConfigInitListener?.requestDataFormDeal(map)
                newMap?.let {
                    dealMap = newMap
                }
            }
            var dealRequest: Request = request
            var dealHttpUrlBuilder: HttpUrl.Builder =  dealRequest.url .newBuilder()
            for (mutableEntry in dealMap) {
                //.addQueryParameter("data", RSAUtils.encrypt(map))  //data是需要的字段
                dealHttpUrlBuilder.addQueryParameter(mutableEntry.key, mutableEntry.value.toString()) //data是需要的字段
            }
            var dealHttpUrl: HttpUrl = dealHttpUrlBuilder.build()

            request = request.newBuilder()
                .post(bodyBuilder.build()) //请求新的请求参数
                .url(dealHttpUrl)
                .build()
        }

        return chain.proceed(request)
    }
}