package com.safmvvm.utils

import androidx.core.content.ContextCompat
import com.safframework.log.L
import com.safframework.log.configL
import com.safframework.log.converter.gson.GsonConverter
import com.safframework.log.debugview.DebugViewWrapper
import com.safframework.log.printer.FilePrinter
import com.safframework.log.printer.file.FileBuilder
import com.safframework.log.printer.file.clean.FileLastModifiedCleanStrategy
import com.safframework.log.printer.file.name.FileNameGenerator
import com.safmvvm.R
import com.safmvvm.app.BaseApp
import com.safmvvm.app.GlobalConfig

object LogUtil {
    //日志Tag
    private val TAG: String = GlobalConfig.appName

    private lateinit var filePrinter:FilePrinter

    /** debugView 背景颜色*/
    private var mDebugColorBg = ContextCompat.getColor(BaseApp.getInstance(), R.color.debug_bg)

    //清理文件日志周期
    const val SEVEN_DAYS:Long = 24*3600*1000*7
    private val cleanStrategy = FileLastModifiedCleanStrategy(SEVEN_DAYS)

    fun initLog() {
        configL { //// 使用自定义的 DSL 来配置 L
            converter = GsonConverter()
        }.apply {
            //日志是否保存到文件中
            if (GlobalConfig.gIsSaveLogToFile) {
                initPrinter()
            }
        }
        //logDebugView开关
        if (GlobalConfig.gIsShowLogDebugView) {
            initDebugView()
        }
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


    // 方便调试的时候，将产生的日志展示到一个特定控件上，看是否有必要使用
    private fun initDebugView() {
        DebugViewWrapper.init(
            DebugViewWrapper.Builder(BaseApp.getInstance())
                .viewWidth(250) /* the width of debug-view */
                .bgColor(mDebugColorBg) /* the color of debug-view */
                .alwaysShowOverlaySetting(true) /* the flag for always showing Overlay Setting */
                .logMaxLines(150) /* the max lines of log */
        )
        DebugViewWrapper.show()
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
    fun json(content: Any){
        L.json(content)
    }

}