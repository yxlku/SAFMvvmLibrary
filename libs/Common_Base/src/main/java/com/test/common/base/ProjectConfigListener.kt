package com.test.common.base

import android.content.Context
import android.widget.ImageView
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.safmvvm.app.AppActivityManager
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.component.RouterUtil
import com.safmvvm.http.ssl.SSLFactory
import com.safmvvm.utils.JsonUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.KVCacheUtil
import com.test.common.BuildConfig
import com.test.common.common.Constants
import com.test.common.RouterActivityPath
import com.test.common.common.ConstantsFun
import com.test.common.common.userInfoToken
import okhttp3.Interceptor
import java.lang.Exception
import kotlin.system.exitProcess

class ProjectConfigListener: GlobalConfigInitListener {
    /**
     * 全局大图长按功能
     * @param imageView 当前ImageView
     * @param imageUrl 图片真实地址
     * @param rv中的位置 或者 iv的位置 通常只有rv才有点用
     */
    override fun initBigPicLongClick(
        context: Context?,
        imageView: ImageView,
        imageUrl: String,
        pos: Int
    ) {

    }

    /**
     * 项目中用到的头信息 -- 不会每次请求调用
     */
    override fun initHeader(): ArrayMap<String, String> {
        LogUtil.d("头信息token")
        var headers: ArrayMap<String, String> = arrayMapOf()
        headers.put("X-Requested-With", "XMLHttpRequest")
//        headers.put("UUID", UUIDUtil.getPhoneSign())
//        headers.put("User-Agent", "")
        return headers
    }
    /**
     * 网络请求头信息 -- 每次请求都会调用此处 - 动态更新的 - 如果key相同也会覆盖头信息
     */
    override fun initHeaderDynamic(): ArrayMap<String, String?> {
        var headers: ArrayMap<String, String?> = arrayMapOf()
        headers.put("token", userInfoToken())
        return headers
    }

    /**
     * 项目中用到的拦截器
     */
    override fun initInterceptor(): ArrayList<Interceptor> {
        var interceptors: ArrayList<Interceptor> = arrayListOf()
//        if (BuildConfig.DEBUG) {
//            interceptors.add(DebugInterceptor())
//        }
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
        ToastUtil.showShortToast("我崩溃了！！错误原因：" + ex?.message )
        AppActivityManager.finishAllActivity()
        exitProcess(0)
    }

    /**
     * 动态替换Url地址，比如： -> (不要在这里搞针对Url追加参数，追加参数的可以在对应的方法中加，全局统一加参数也在对应方法加)
     * 1、针对某个地址，完全替换
     * 2、针对一系列规则的地址，进行修改
     *
     * @param oldUrl 原始url完整地址
     *
     * @return 返回更改的url - 如果返回null则代表不修改
     */
    override fun requestBtReplaceUrl(oldUrl: String?): String? {
        oldUrl?.apply {
            when {
                this.contains("/DETI-System") -> {
                    return this.replace("/DETI-System", ":9001/DETI-System", ignoreCase = true)
                }
                this.contains("/DETI-Demand") -> {
                    return this.replace("/DETI-Demand", ":9002/DETI-Demand", ignoreCase = true)
                }
            }
        }
        return null
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
//                param.put("timestamp", (System.currentTimeMillis() / 1000).toString())
                param.put("timestamp", "2147483647")
                param.put("sign", "2147483647")
                return JsonUtil.toJson(param)
            }
        }
        return dataPlaintext+""
    }

    /**
     * 统一处理加密解密
     */
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
//                b.errorMsg = "我擦，我解析成功了！！！！！！！"
//                b.errorCode = "300"
                return JsonUtil.toJson(b)
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
            ToastUtil.showShortToast(msg + "我退出登录了！！")
            //清空内存
            //.....
            //1、清空token
            ConstantsFun.User.logoutClearInfo()
            //退出登录
            RouterUtil.startActivity(RouterActivityPath.ModuleBasis.PAGE_LOGIN)
            return true
        }else{
            return false
        }

    }
}