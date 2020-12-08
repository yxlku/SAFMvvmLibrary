package com.longpc.testapplication

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.longpc.testapplication.base.BaseNetEntity
import com.longpc.testapplication.base.BaseNetEntityPost
import com.safmvvm.app.config.GlobalConfigInitListener
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import okhttp3.Interceptor
import java.lang.Exception

class ProjectConfigListener: GlobalConfigInitListener {

    /**
     * 项目中用到的头信息
     */
    override fun initHeader(): ArrayMap<String, String> {
        var headers: ArrayMap<String, String> = arrayMapOf()
        headers.put("test1", "我是测试Header1")
        headers.put("test2", "我是测试Header2")
        headers.put("test2", "我是测试Header3")
        headers.put("User-Agent", "CHrome111")
        return headers
    }

    /**
     * 项目中用到的拦截器
     */
    override fun initInterceptor(): ArrayList<Interceptor> {
        var interceptors: ArrayList<Interceptor> = arrayListOf()
        if (BuildConfig.DEBUG) {
            interceptors.add(DebugInterceptor())
        }
        return interceptors
    }

    /**
     * 全局异常捕获处理
     */
    override fun initCrashHandlerDeal(thread: Thread?, ex: Throwable?) {
        ToastUtil.showShortToast("我擦，我崩溃了！！错误原因：" + ex?.message )
    }

    override fun dateParseException(isJson: Boolean, msg: String, ex: Exception) {
        //解析异常
        LogUtil.e(msg)
    }

    /**
     * Get请求处理
     */
    override fun requestDataGetDeal(dataSouce: HashMap<String, String?>): HashMap<String, String?> {
        var newDataSouce = HashMap<String, String?>()
        dataSouce.forEach { (key, value) ->
            newDataSouce.put(key, value+"添加")
        }
        newDataSouce.put("ty1", "tyc1")
        newDataSouce.put("ty2", "tyc2")
        return newDataSouce
    }

    /**
     * Form表单提交
     * 1、可对单独数据进行加密
     * 2、可以取出所有数据进行加密
     * 3、可以封装通用参数
     */
    override fun requestDataFormDeal(map: HashMap<String, Any>): HashMap<String, Any> {
        var dealMap: HashMap<String, Any> = HashMap()
        for (mutableEntry in map) {
            dealMap[mutableEntry.key] = "22222${mutableEntry.value}1111"
        }
        return dealMap
    }


    /**
     * Post请求统一处理
     * 1、统一参数
     * 2、参数加密
     */
    override fun requestDataBodyDeal(dataPlaintext: String?): String {
        //请求统一处理
        dataPlaintext?.let {
            //通用参数、
            var param = JsonUtil.getJsonParseMapResult(dataPlaintext)
            param?.let {
                param.put("test", "ssss")
                param.put("test1", "ssss2")
                param.put("test3", "ssss3")
                return JsonUtil.toJson(param!!)+"ssssss沙发上"
            }
        }
        return dataPlaintext+""
    }

    override fun responseDataDeal(dataSouce: String?): String {
        //返回统一处理
        dataSouce?.let {
            var b: BaseNetEntity<*>? = JsonUtil.getJsonParseResult(dataSouce, BaseNetEntity::class.java)
            b?.let {
                b.errorMsg = "我擦，我解析成功了！！！！！！！"
                return JsonUtil.toJson(b)
//                return "我擦，啥情况啊！！！！！！"
            }
        }
        return dataSouce+""
    }
}