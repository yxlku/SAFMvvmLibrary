package com.safmvvm.utils

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.orhanobut.logger.*
import com.safmvvm.app.globalconfig.GlobalConfig


/**
 * 日志工具
 */
object LogUtil {

    /**
     * BaseApp中初始化，不用其他组件初始化，其他组件只需要修改GlobalConfig的值即可
     */
    fun initLog(){
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)  // 是否选择显示线程信息，默认为true
            .methodCount(2)         // 方法数显示多少行，默认2行
            .methodOffset(5)        // 隐藏方法内部调用到偏移量，默认5
            .tag(GlobalConfig.Log.gLogTag) // 打印日志的策略，默认LogCat
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                //通过配置可以开启关闭日志
                return GlobalConfig.Log.gIsOpenLog
            }
        })
        val formatStrategyDisk: FormatStrategy = CsvFormatStrategy.newBuilder()
            .tag(GlobalConfig.Log.gLogTag)
            .build()
        //缓存到文件
        Logger.addLogAdapter(DiskLogAdapter(formatStrategyDisk))
    }

    /**
     * 通过子Base组件来控制统一配置开关
     */
    fun configLogInterceptor(): LoggingInterceptor {
        var level = if(GlobalConfig.Log.gIsOpenLog) Level.BASIC else Level.NONE
        return LoggingInterceptor.Builder()
            .tag(GlobalConfig.Log.gLogTag)
            .setLevel(level)
            .build()
    }


    fun v(content: String, tag: String = GlobalConfig.Log.gLogTag) {
        Logger.t(tag).v(content)
    }

    fun d(content: String, tag: String = GlobalConfig.Log.gLogTag) {
        Logger.t(tag).d(content)
    }

    fun i(content: String, tag: String = GlobalConfig.Log.gLogTag) {
        Logger.t(tag).i(content)
    }

    fun w(content: String, tag: String = GlobalConfig.Log.gLogTag) {
        Logger.t(tag).w(content)
    }

    fun e(content: String, tag: String = GlobalConfig.Log.gLogTag) {
        Logger.t(tag).e(content)
    }

    /**
     * 日志json输出（全部对象类型的转行Json）
     */
    fun json(content: String){
        Logger.json(content)
    }

    /**
     * 输出异常信息
     */
    fun exception(msg: String ="异常", ex: Throwable){
        Logger.e(ex, msg)
    }

    /**
     * 输出任意对象信息
     */
    fun any(msg: String, any: Any){
        Logger.d(msg, any)
    }

}