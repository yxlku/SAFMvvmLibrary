package com.safmvvm.app

import android.os.Build
import com.safmvvm.app.config.GlobalConfig
import com.safmvvm.utils.AppUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.currentTimeMills
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Thread.UncaughtExceptionHandler
import kotlin.system.exitProcess

/**
 * 崩溃信息捕获，存储在 /sdcard/Android/data/xxx.xxx.xxx/cache/Log/crash/日期.log
 */
internal object CrashHandlerUtil : UncaughtExceptionHandler {

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        //打印日志
        handleException(ex)
        //系统输出错误信息
        ex.printStackTrace()
        if (GlobalConfig.App.gGlobalConfigInitListener != null) {
            GlobalConfig.App.gGlobalConfigInitListener?.initCrashHandlerDeal(thread, ex)
        }else {
            //如果不配置监听则默认退出App
            AppActivityManager.finishAllActivity()
            exitProcess(0)
        }
    }

    private fun formatLogInfo(ex: Throwable): String {
        val lineSeparator = "\r\n"

        val sb = StringBuilder()
        val logTime = "logTime:" + currentTimeMills

        val exception = "exception:$ex"

        val info = StringWriter()
        val printWriter = PrintWriter(info)
        ex.printStackTrace(printWriter)

        val dump = info.toString()

        val crashDump = "crashDump:{$dump}"
        printWriter.close()

        sb.append(lineSeparator)
        sb.append("&start---").append(lineSeparator)
        sb.append(logTime).append(lineSeparator)
        sb.append("appVerName:").append(AppUtil.versionName).append(lineSeparator)
        sb.append("appVerCode:").append(AppUtil.versionCode).append(lineSeparator)
        // 系统版本
        sb.append("OsVer:").append(Build.VERSION.RELEASE).append(lineSeparator)
        // 手机厂商
        sb.append("vendor:").append(Build.MANUFACTURER).append(lineSeparator)
        // 型号
        sb.append("model:").append(Build.MODEL).append(lineSeparator)
        sb.append(exception).append(lineSeparator)
        sb.append(crashDump).append(lineSeparator)
        sb.append("&end---").append(lineSeparator).append(lineSeparator).append(lineSeparator)

        return sb.toString()
    }

    private fun handleException(ex: Throwable) {
        //打印日志，日志工具自带保存到文件
        LogUtil.e(formatLogInfo(ex))
    }
}
