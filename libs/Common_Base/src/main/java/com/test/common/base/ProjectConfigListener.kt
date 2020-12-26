package com.test.common.base

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.safmvvm.app.AppActivityManager
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.component.RouterUtil
import com.safmvvm.http.ssl.SSLFactory
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.UUIDUtil
import com.test.common.BuildConfig
import com.test.common.RouterActivityPath
import okhttp3.Interceptor
import java.lang.Exception
import kotlin.system.exitProcess

class ProjectConfigListener: GlobalConfigInitListener {

    /**
     * 项目中用到的头信息
     */
    override fun initHeader(): ArrayMap<String, String> {
        var headers: ArrayMap<String, String> = arrayMapOf()
        headers.put("test1", "我是测试Header1")
        headers.put("test2", "我是测试Header2")
        headers.put("test2", "我是测试Header3")
        headers.put("UUID", UUIDUtil.getPhoneSign())
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
     * 自定义SSL认证，如果返回null，则框架内会使用过滤所有的认证，
     *
     * 使用SSLFactory类进行控制
     *
     * TODO 还没有尝试过，找个Https尝试下
     */
    override fun initSSL(): SSLFactory.SSLParams? {
        return null
    }

    /**
     * 全局异常捕获处理
     */
    override fun initCrashHandlerDeal(thread: Thread?, ex: Throwable?) {
        ToastUtil.showShortToast("我擦，我崩溃了！！错误原因：" + ex?.message )
        AppActivityManager.finishAllActivity()
        exitProcess(0)
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
                return JsonUtil.toJson(param)+"ssssss沙发上"
            }
        }
        return dataPlaintext+""
    }

    override fun responseDataDeal(dataSouce: String?): String {
        //返回统一处理
        dataSouce?.let {
            /**
                // 公钥加密私钥解密
                byte[] rsaPublic = RSAUtil.rsa(data, publicKey, RSAUtil.RSA_PUBLIC_ENCRYPT);
                System.out.println("rsa公钥加密： " + new String(rsaPublic));
                System.out.println("rsa私钥解密： " + new String(RSAUtil.rsa(rsaPublic, privateKey, RSAUtil.RSA_PRIVATE_DECRYPT)));
             */
            var b: BaseNetEntity<*>? = JsonUtil.getJsonParseResult(dataSouce, BaseNetEntity::class.java)
            b?.let {
                b.errorMsg = "我擦，我解析成功了！！！！！！！"
//                b.errorCode = "300"
                return JsonUtil.toJson(b)
//                return "我擦，啥情况啊！！！！！！"
            }
        }
        return dataSouce+""
    }

    /**
     * 服务器返回错误码统一处理，code 不等于 GlobalConfig.Request.SUCCESS_CODE
     * 例如：
     *  1、token失效，跳转到登录页面
     *  2、指定错误码，打开webview，msg为url，防止功能不能正常使用
     *
     * @return true 显示错误状态页面 false，不显示状态页面
     *
     * 1、无论返回true和false都会隐藏等待状态页面
     * 2、无论返回true和false都会回到onFaile()函数，具体方法可以自行在VM中处理
     */
    override fun dealNetCode(code: String, msg: String?): Boolean {
        if (code == "300") {
            ToastUtil.showShortToast(msg + "我擦，我退出登录了！！")
            //清空内存
            //.....
            //退出登录
            RouterUtil.startActivity(RouterActivityPath.ModuleBasis.PAGE_LOGIN)
            return true
        }else{
            return false
        }

    }
}