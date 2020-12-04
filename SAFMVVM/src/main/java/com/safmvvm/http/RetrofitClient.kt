package com.safmvvm.http

import android.content.Context
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.safmvvm.app.BaseApp
import com.safmvvm.app.config.GlobalConfig
import com.safmvvm.http.cookie.CookieJarImpl
import com.safmvvm.http.cookie.store.PersistentCookieStore
import com.safmvvm.http.interceptor.HeaderInterceptor
import com.safmvvm.utils.LogUtil
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *
 * 目的1：没网的时候，尝试读取缓存，避免界面空白，只需要addInterceptor和cache即可（已实现）
 * 目的2：有网的时候，总是读取网络上最新的，或者设置一定的超时时间，比如10秒内有多个同一请求，则都从缓存中获取（没实现）
 * 目的3：不同的接口，不同的缓存策略（？）
 *
 */
class RetrofitClient private constructor(){
    companion object {
        //单例
        val instance: RetrofitClient by lazy { RetrofitClient() }
    }

    private val mContext: Context = BaseApp.getInstance()
    // 缓存 service
    private val mServiceMap = ArrayMap<String, Any>()

    /**
     * 如果有不同的 baseURL，那么可以相同 baseURL 的接口都放在一个 Service 钟，通过此方法来获取
     */
    fun <T> getService(cls: Class<T>, host: String, headers: ArrayMap<String, String>?, interceptors: ArrayList<Interceptor>?): T {
        var name = cls.name

        var obj = mServiceMap[name]
        if (obj == null) {
            //默认头信息
            var headers = initHeaders(headers)
            //初始化okHttpClient
            var okHttpClient = initOkHttpClient(headers, interceptors)
            //初始化RetrofitBuilder
            var retrofitBuilder: Retrofit.Builder = initRetrofitBuilder(host, okHttpClient)
            //动态改变baseUrl 是否需要动态修改 BaseURL，如果需要，请设置GlobalConfig.gIsNeedChangeBaseUrl为true
            // ，并在合适的位置调用：[RetrofitClient.multiClickToChangeBaseUrl]
//            initDynamicChangeBaseUrl(host, okHttpClient, retrofitBuilder)

            obj = retrofitBuilder.build().create(cls)
            mServiceMap[name] = obj
        }
        return obj as T
    }

    /**
     * 使用子Moudle中配置的头信息
     */
    fun <T> getService(cls: Class<T>): T?{
        if (GlobalConfig.Request.gBaseUrl.isBlank()) {
            throw RuntimeException("必须初始化 BASE_URL")
        }

        //头信息
        var headers = GlobalConfig.App.gGlobalConfigInitListener?.initHeader()
        //拦截器
        var interceptors = GlobalConfig.App.gGlobalConfigInitListener?.initInterceptor()
        return getService(cls, GlobalConfig.Request.gBaseUrl, headers, interceptors)
    }

    /**
     * 头信息 -- 统一入口
     */
    fun initHeaders(otherHeaders: ArrayMap<String, String>?): ArrayMap<String, String>{
        var headers = ArrayMap<String, String>()
        headers.put("testHeader", "OkHeader")
        //添加其他的头信息
        otherHeaders?.let {
            otherHeaders.forEach {
                headers.put(it.key, it.value)
            }
        }
        return headers
    }
    /**
     * 初始化OkHttpClient
     */
    fun initOkHttpClient(headers: ArrayMap<String, String> , interceptors: ArrayList<Interceptor>?): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            .cookieJar(CookieJarImpl(PersistentCookieStore(mContext)))  //TODO cookie信息，目前是用Sp来实现存储的
            .addInterceptor(HeaderInterceptor(headers))     //头信息拦截器
            .addNetworkInterceptor(LogUtil.configLogInterceptor()) //日志拦截器
            .connectTimeout(GlobalConfig.Request.DEFAULT_TIMEOUT, TimeUnit.SECONDS)          //连接超时时间
            .writeTimeout(GlobalConfig.Request.DEFAULT_TIMEOUT, TimeUnit.SECONDS)            //读取超时时间
            .retryOnConnectionFailure(false)
            // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
        //添加从外部传来的拦截器
        interceptors?.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        return okHttpClientBuilder.build()
    }
    /**
     * 初始化RetrofitBuilder
     */
    private fun initRetrofitBuilder(host: String, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) //gson解析
            .baseUrl(host)
    }

}
