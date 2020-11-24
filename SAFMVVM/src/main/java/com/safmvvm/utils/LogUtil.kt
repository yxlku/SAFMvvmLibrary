package com.safmvvm.utils

import androidx.core.content.ContextCompat
import com.imyyq.mvvm.utils.CrashHandlerUtil
import com.safframework.log.L
import com.safframework.log.LogLevel
import com.safframework.log.bean.JSONConfig
import com.safframework.log.configL
import com.safframework.log.converter.gson.GsonConverter
import com.safframework.log.debugview.DebugViewWrapper
import com.safframework.log.debugview.modules.TimerModule
import com.safframework.log.extension.json
import com.safframework.log.printer.FilePrinter
import com.safframework.log.printer.Printer
import com.safframework.log.printer.file.FileBuilder
import com.safframework.log.printer.file.clean.FileLastModifiedCleanStrategy
import com.safframework.log.printer.file.name.FileNameGenerator
import com.safmvvm.R
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig
import java.util.*

object LogUtil {
    /** 日志Tag */
    private val TAG: String = GlobalConfig.appName
    /** 文件*/
    private lateinit var filePrinter:FilePrinter

    //清理文件日志周期
    const val SEVEN_DAYS:Long = 24*3600*1000*7
    private val cleanStrategy = FileLastModifiedCleanStrategy(SEVEN_DAYS)

    fun initLog() {
        //设置默认Tag
        L.init(TAG)
        configL { //// 使用自定义的 DSL 来配置 L
            converter = GsonConverter()
        }
        //日志是否保存到文件中
        if (GlobalConfig.gIsSaveLogToFile) {
            initPrinter()
        }
        //开启全局日常捕获
        CrashHandlerUtil.init()
    }
    /**
     * 日志存储到本地初始化
     */
    private fun initPrinter(){
        filePrinter = FileBuilder()
            .folderPath(GlobalConfig.gLogSaveFileDir)
            .fileNameGenerator(object :FileNameGenerator{
                //日志文件名
                override fun generateFileName(
                    logLevel: Int,
                    tag: String,
                    timestamp: Long,
                    lastFileName: String
                ): String {
                    return GlobalConfig.gLogFileBaseName +
                            logLevel +
                            "_" + timestamp.format2DateString(DefaultDateFormat.DATE_YMD)+
                            ".log"
                }
                override fun isFileNameChangeable(): Boolean {
                    //日志名字是否可以改变
                    return true
                }
            })
            .cleanStrategy(cleanStrategy)
            .build()
        L.addPrinter(filePrinter);
    }

    fun e(tag: String, content: String?){
        L.e(tag, content)
    }

    fun e(content: String?){
        e(TAG, content)
    }

    fun w(tag: String, content: String?){
        L.w(tag, content)
    }

    fun w(content: String?){
        w(TAG, content)
    }

    fun i(tag: String, content: String?){
        L.i(tag, content)
    }

    fun i(content: String?){
        i(TAG, content)
    }

    fun d(tag: String, content: String?){
        L.d(tag, content)
    }

    fun d(content: String?){
        d(TAG, content)
    }

    /**
     * 日志json输出（全部对象类型的转行Json）
     * JSONString、List、Set、Map、Reference、Throwable、Bundle、Intent、Uri
     */
    fun json(content: Any?){
        L.json(content)
    }

}