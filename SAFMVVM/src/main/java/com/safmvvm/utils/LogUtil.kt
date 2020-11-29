package com.safmvvm.utils

import com.imyyq.mvvm.utils.CrashHandlerUtil
import com.orhanobut.logger.*
import com.safmvvm.app.GlobalConfig


/**
 * 日志工具
 */
object LogUtil {

    fun initLog(){
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.addLogAdapter(DiskLogAdapter())
        //开启全局日常捕获
        CrashHandlerUtil.init()
    }


    fun v(tag: String, content: String) {
        Logger.t(tag).v(content)
    }

    fun v(content: String) {
        v(GlobalConfig.Cache.gLogTag, content)
    }

    fun d(tag: String, content: String) {
        Logger.t(tag).d(content)
    }

    fun d(content: String) {
        d(GlobalConfig.Cache.gLogTag, content)
    }

    fun i(tag: String, content: String) {
        Logger.t(tag).i(content)
    }

    fun i(content: String) {
        i(GlobalConfig.Cache.gLogTag, content)
    }

    fun w(tag: String, content: String) {
        Logger.t(tag).w(content)
    }

    fun w(content: String) {
        w(GlobalConfig.Cache.gLogTag, content)
    }
    fun e(tag: String, content: String) {
        Logger.t(tag).e(content)
    }

    fun e(content: String) {
        e(GlobalConfig.Cache.gLogTag, content)
    }
    /**
     * 日志json输出（全部对象类型的转行Json）
     */
    fun json(content: String){
        Logger.json(content)
    }

    fun exception(msg: String, ex: Throwable){
        Logger.wtf(msg, ex)
    }

    fun any(msg:String, any: Any){
        Logger.e(msg, any)
    }


}