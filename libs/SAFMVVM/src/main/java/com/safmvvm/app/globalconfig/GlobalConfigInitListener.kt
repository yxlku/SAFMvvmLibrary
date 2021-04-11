package com.safmvvm.app.globalconfig

import android.content.Context
import android.widget.ImageView
import androidx.collection.ArrayMap
import com.safmvvm.http.ssl.SSLFactory
import okhttp3.Interceptor
import java.lang.Exception

/**
 * 初始化接口，配合一些库需要通过自定义方法来自定义初始化
 */
interface GlobalConfigInitListener {

    /**
     * 全局大图长按功能
     * @param imageView 当前ImageView
     * @param imageUrl 图片真实地址
     * @param rv中的位置 或者 iv的位置 通常只有rv才有点用
     */
    fun initBigPicLongClick(context: Context?, imageView: ImageView, imageUrl: String, pos: Int)

    /**
     * 网络请求头信息
     */
    fun initHeader(): ArrayMap<String, String>

    /**
     * 网络请求头信息 - 动态更新的 - 如果key相同也会覆盖头信息
     */
    fun initHeaderDynamic(): ArrayMap<String, String?>

    /**
     * 动态添加拦截器
     */
    fun initInterceptor(): ArrayList<Interceptor>

    /**
     * 自定义SSL认证，如果返回null，则框架内会使用过滤所有的认证，
     *
     * 使用SSLFactory类进行控制
     */
    fun initSSL(): SSLFactory.SSLParams?

    /**
     * 全局异常捕获处理
     */
    fun initCrashHandlerDeal(thread: Thread?, ex: Throwable?)

    /**
     * 动态替换Url地址，比如： -> (不要在这里搞针对Url追加参数，追加参数的可以在对应的方法中加，全局统一加参数也在对应方法加)
     * 1、针对某个地址，完全替换
     * 2、针对一系列规则的地址，进行修改
     *
     * @param oldUrl 原始url完整地址
     *
     * @return 返回更改的url - 如果返回null则代表不修改
     */
    fun requestBtReplaceUrl(oldUrl: String?): String?

    /**
     * 网络请求解析
     * @param isJson 是否是Json，具体操作自行处理即可
     */
    fun dateParseException(isJson: Boolean, msg: String, ex: Exception)

    /**
     * （Get请求方式）请求数据处理 -- 如果不需要Get请求或不需要对Get请求处理，则直接返回参数即可
     * 例如：(具体根据业务来定)
     *   1、对可以对Key加密
     *   2、可以对Value加密
     *   3、追加默认统一参数
     */
    fun requestDataGetDeal(dataSouce: HashMap<String, String?>): HashMap<String, String?>

    /**
     * （Form表单方式）请求数据处理 -- ** 如果项目中不需要则不需要实现任何操作，返回空或者参数就可以**
     * 例如：
     *   1、可对单独数据进行加密
     *   2、可以取出所有数据进行加密
     *   3、可以封装通用参数
     *
     * @return 如果加密了，则直接通过在返回的Map中的key设置服务器需要的字段，对应的value设置加密后的数据
     */
    fun requestDataFormDeal(map: HashMap<String, Any>): HashMap<String, Any>

    /**
     * （Body方式）请求数据处理 - 会把明文参数转换为Json，处理时自行转换
     * 例如：
     *  1、加密；
     *  2、统一参数封装；
     *  3、.....
     *
     *  @return 返回处理后的结果
     */
    fun requestDataBodyDeal(dataPlaintext: String?): String


    /**
     * 返回的数据处理
     * 例如：
     *  1、解密
     *  2、数据统一处理
     *  3、返回code处理
     *  4、....
     *
     *  @return 返回处理后的请求结果
     */
    fun responseDataDeal(dataSouce: String?): String

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
    fun dealNetCode(code: String, msg: String?): Boolean
}