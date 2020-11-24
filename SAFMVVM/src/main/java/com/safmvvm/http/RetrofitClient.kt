package com.safmvvm.http

import android.content.Context
import androidx.collection.ArrayMap
import com.safframework.log.LogLevel
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig
import com.safmvvm.http.cookie.CookieJarImpl
import com.safmvvm.http.cookie.store.PersistentCookieStore
import com.safmvvm.http.interceptor.HeaderInterceptor
import com.safmvvm.http.interceptor.LoggingInterceptor
import com.safmvvm.utils.KVCacheUtil
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
    companion object{
        //单例
        val instance: RetrofitClient by lazy{RetrofitClient()}
    }
    /** baseUrl */
    lateinit var BASE_URL: String
    /** 超时时间 */
    val DEFAULT_TIMEOUT: Long = 20
    /** 请求超时时间，秒为单位 */
    var DEFAULT_TIME_OUT = 10

    /** 存储 baseUrl，以便可以动态更改 */
    private lateinit var mBaseUrlMap: ArrayMap<String, String>
    private val mContext: Context = BaseApp.getInstance()
    // 缓存 service
    private val mServiceMap = ArrayMap<String, Any>()
    /** host是否缓存*/
    private val mKeyIsSave = "is_save"

    /**
     * 如果有不同的 baseURL，那么可以相同 baseURL 的接口都放在一个 Service 钟，通过此方法来获取
     */
    fun <T> getService(cls: Class<T>, host: String, headers: ArrayMap<String, String>?, interceptors: MutableList<Interceptor>?): T {
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
            initDynamicChangeBaseUrl(host, okHttpClient, retrofitBuilder)

            obj = retrofitBuilder.build().create(cls)
            mServiceMap[name] = obj
        }
        return obj as T
    }
    fun <T> getService(cls: Class<T>): T?{
        if (!this::BASE_URL.isInitialized) {
            throw RuntimeException("必须初始化 BASE_URL")
        }
        var headers = ArrayMap<String, String>()
        return getService(cls, BASE_URL, headers, null)
    }

    /**
     * 头信息
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
    fun initOkHttpClient(headers: ArrayMap<String, String> , interceptors: MutableList<Interceptor>?): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
        //超时时间
        okHttpClientBuilder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)

        //添加从外部传来的拦截器
        interceptors?.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        //日志拦截
        okHttpClientBuilder.addInterceptor(
            LoggingInterceptor.Builder()
                .logLevel(LogLevel.INFO)
                .build()
        )
        okHttpClientBuilder
            .cookieJar(CookieJarImpl(PersistentCookieStore(mContext)))
            .addInterceptor(HeaderInterceptor(headers))
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)          //连接超时时间
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)            //读取超时时间
            // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
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
    /**
     * 初始化动态更改baseUrl
     */
    private fun initDynamicChangeBaseUrl(host: String, okHttpClient: OkHttpClient, retrofitBuilder: Retrofit.Builder) {
        if (GlobalConfig.gIsNeedChangeBaseUrl) {
            //支持动态改变baseUrl
            if (!this::mBaseUrlMap.isInitialized) {
                mBaseUrlMap = ArrayMap()
            }
            var isSave = KVCacheUtil.getBoolean(mKeyIsSave)
            isSave?.let {
                if (it) {
                    mBaseUrlMap[host] = KVCacheUtil.getString(host)
                } else {
                    mBaseUrlMap[host] = ""
                }
            }
            retrofitBuilder.callFactory {
                LogUtil.i("HttpRequest", "getService: old ${it.url()}")
                mBaseUrlMap.forEach { entry ->
                    val key = entry.key
                    var value = entry.value
                    val url = it.url().toString()
                    if (url.startsWith(key) && value.isNotEmpty()) {
                        // 防止尾缀有问题
                        if (key.endsWith("/") && !value.endsWith("/")) {
                            value += "/"
                        } else if (!key.endsWith("/") && value.endsWith("/")) {
                            value = value.substring(0, value.length - 1)
                        }
                    }
                    // 替换 url 并创建新的 call
                    val newRequest: Request =
                        it.newBuilder()
                            .url(HttpUrl.get(url.replaceFirst(key, value)))
                            .build()
                    LogUtil.i("HttpRequest", "getService: new ${newRequest.url()}")
                    //TODO Test 会将原有OkHttp拦截器覆盖掉吗？
                    return@callFactory okHttpClient.newCall(newRequest)
                }
                okHttpClient.newCall(it)
            }
        }
    }

}