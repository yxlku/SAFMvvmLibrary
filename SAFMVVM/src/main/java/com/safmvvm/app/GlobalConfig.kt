package com.safmvvm.app

import androidx.annotation.LayoutRes
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.safmvvm.ui.load.loadsir.callback.DefaultEmptyCallback
import com.safmvvm.ui.load.loadsir.callback.DefaultErrorCallback
import com.safmvvm.ui.load.loadsir.callback.DefaultLoadingCallback
import com.safmvvm.ui.load.loadsir.callback.DefaultTimeOutCallback
import com.safmvvm.utils.FileUtil


/**
 * 全局配置
 * 开关默认都关闭，在子Module中初始化，自行开启即可
 */
object GlobalConfig {

    const val appName = "SAFMVVM"

    object App{
        /**  是否需要管理 Activity 堆栈 */
        var gIsNeedActivityManager = false
    }

    /**
     * 日志
     */
    object Log{
        /** 全局日志tag，默认为app的名字*/
        var gLogTag = appName
        /** 是否开启Log*/
        var gIsOpenLog = false

        /** 是否保存日志到文件中*/
        var gIsSaveLogToFile = false
        /** 日志保存文件位置 取得App的log目录：/sdcard/包名/Log，没有sd卡则是/data/data/包名/files/Log*/
        var gLogSaveFileDir = FileUtil.appLogDir
        /** 日志基础名字*/
        var gLogFileBaseName = "Log_"
    }

    /**
     * 请求可配置项
     */
    object Request{
        /** baseUrl*/
        var gBaseUrl: String = ""
        /** 请求等待显示信息, 可以在子Module中配置全局，也可以在实现中，具体实现方法中修改对应的内容*/
        var gLodingMsg: String = "正在加载中，请稍候。。。"
        /** 必须设置*/
        var SUCCESS_CODE: String = ""
        /** 超时时间 */
        var DEFAULT_TIMEOUT: Long = 20
        /** 请求超时时间，秒为单位 */
        var DEFAULT_TIME_OUT = 10
    }
    /**
     * 缓存
     */
    object Cache{

    }

    /**
     * 等待布局或弹窗配置属性
     */
    object Loading{
        /** 等待时显示的文字*/
        var LOADING_TEXT: String = ""
        /** 自定义等待弹窗，这里需要传入自定义Layout的Id，则要求必须有id为tv_title的TextView，否则无任何要求*/
        var LOADING_LAYOUT_ID: Int = 0

        var CALLBACK_LOADING: Class<out Callback>? = null
        var CALLBACK_EMPTY: Class<out Callback>? = null
        var CALLBACK_FAIL: Class<out Callback>? = null
        var CALLBACK_NET_ERROR: Class<out Callback>? = null
    }

    /**
     * 初始化 LoadSir 的相关界面。
     * [defCallback] 默认的界面，通常是加载中页面，设置了后，默认打开开启了 LoadSir 的页面后就显示这里设置的页面。
     * [clazz] 其他的状态页，比如空页面，加载错误等。
     */
    fun initLoadSir(
        defCallback: Class<out Callback>?,
        //等待页面，如果不设置调用框架内等待页面
        loadingCallback: Class<out Callback> = DefaultLoadingCallback::class.java,
        //空布局
        emptyCallback: Class<out Callback> = DefaultEmptyCallback::class.java,
        //错误布局
        failCallback: Class<out Callback> = DefaultErrorCallback::class.java,
        //网络错误
        netErroyCallback: Class<out Callback> = DefaultTimeOutCallback::class.java,
        vararg clazz: Class<out Callback>
    ) {
        Loading.CALLBACK_LOADING = loadingCallback
        Loading.CALLBACK_EMPTY = emptyCallback
        Loading.CALLBACK_FAIL = failCallback
        Loading.CALLBACK_NET_ERROR = netErroyCallback

        val builder = LoadSir.beginBuilder()
        clazz.forEach {
            builder.addCallback(it.newInstance())
        }
        builder
            //等待页面
            .addCallback(loadingCallback.newInstance())
            //空布局
            .addCallback(emptyCallback.newInstance())
            //请求成功但是服务器返回错误布局
            .addCallback(failCallback.newInstance())
            //网络请求错误布局
            .addCallback(netErroyCallback.newInstance())
            //设置默认状态页 如果传入为空，则显示成功页面
            .setDefaultCallback(if(defCallback == null) SuccessCallback::class.java else defCallback)
            .commit()
    }


}