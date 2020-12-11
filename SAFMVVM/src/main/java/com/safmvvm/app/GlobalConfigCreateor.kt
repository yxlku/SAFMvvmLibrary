package com.safmvvm.app

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.room.RoomDatabase
import com.safmvvm.R
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.app.globalconfig.GlobalConfigFun
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.db.MigrationManager
import com.safmvvm.file.update.dialog.IUpdateProgressDialog
import com.safmvvm.utils.FileUtil
import com.zy.multistatepage.MultiState

/**
 * 1、统一调用位置
 * 2、统一分配调用对象，不用子Module来想给谁分配
 * 3、赋值的时候可以增加一些限制
 *
        fun method(): GlobalConfigBuiler{
            。。。。
            return this
        }
 */
class GlobalConfigCreateor {

    /************************************ App全局性请求初始化配置 *******************************************/
    fun setGlobalConfigInitListener(listener: GlobalConfigInitListener): GlobalConfigCreateor{
        GlobalConfig.App.gGlobalConfigInitListener = listener
        return this
    }
    /************************************ Request请求初始化配置 *******************************************/
    /**
     * 全局统一BaseUrl
     */
    fun requestBaseUrl(baseUrl: String): GlobalConfigCreateor{
        GlobalConfig.Request.gBaseUrl = baseUrl
        return this
    }

    /**
     * 默认成功状态码
     */
    fun requestSuccessCode(successCode: String): GlobalConfigCreateor{
        GlobalConfig.Request.SUCCESS_CODE = successCode
        return this
    }

    /**
     * 默认请求超时时间
     * 单位：s秒
     */
    fun requestTimeOut(timeout: Long): GlobalConfigCreateor{
        GlobalConfig.Request.DEFAULT_TIMEOUT = timeout
        return this
    }
    /************************************ 日志初始化配置 *******************************************/
    /**
     * 日志是否开启
     */
    fun logIsOpen(isOpen: Boolean): GlobalConfigCreateor{
        GlobalConfig.Log.gIsOpenLog = isOpen
        return this
    }
    /**
     * 全局日志Log的Tag
     */
    fun logTag(tag: String): GlobalConfigCreateor{
        GlobalConfig.Log.gLogTag = tag
        return this
    }
    /**
     * 日志是否保存到文件中
     */
    fun logIsSaveLogToFile(isSaveLogToFile: Boolean): GlobalConfigCreateor{
        GlobalConfig.Log.gIsSaveLogToFile = isSaveLogToFile
        return this
    }

    /********************************** 数据库初始化配置 *******************************************/
    /**
     * 1、设置数据库名称， 一个App只有一个DB，别整那么多数据库，操作表就行
     *
     * 2、如果实在想多个数据库就直接用RoomUtil
     */
    fun dbName(dbName: String): GlobalConfigCreateor{
        GlobalConfig.DB.gDBName = dbName
        return this
    }

    /**
     * 管理数据库升级版本
     */
    fun dbConfigMigrationManager(migrationManager: MigrationManager): GlobalConfigCreateor{
        GlobalConfig.DB.gMigrationManager = migrationManager
        return this
    }

    /********************************** 版本更新初始化配置 *******************************************/
//    /** apk下载地址*/
//    fun updateApkDownloadPath(downloadPath: String): GlobalConfigCreateor{
//        GlobalConfig.Update.gApkDownloadPath = downloadPath
//        return this
//    }
//    /** 版本号*/
//    fun updateCurrentVersion(currentVersion: String): GlobalConfigCreateor{
//        GlobalConfig.Update.gCurrentVersion = currentVersion
//        return this
//    }
    /** apk存放路径, 有sd卡 /sdcard/包名/File，没有sd卡则是/data/data/包名/files/File*/
    fun updateApkFilePath(apkFilePath: String): GlobalConfigCreateor{
        GlobalConfig.Update.gApkFilePath = apkFilePath
        return this
    }
    /** 下载名称, 默认为应用名*/
    fun updateApkName(apkName: String): GlobalConfigCreateor{
        GlobalConfig.Update.gApkName = apkName
        return this
    }

    /** 等待样式，布局ID，控件Id要保持一致*/
    fun updateUpdateProgress(@LayoutRes updateProgress: Int): GlobalConfigCreateor{
        GlobalConfig.Update.gUpdateProgress = updateProgress
        return this
    }
    /** 自定义更新等待弹窗*/
    fun updateUpdateProgressDialog(updateProgressDialog: IUpdateProgressDialog): GlobalConfigCreateor{
        GlobalConfig.Update.gUpdateProgressDialog = updateProgressDialog
        return this
    }

    /********************************** 请求状态时初始化配置 *******************************************/
    /**
     * 全局统一等待弹窗布局ID
     *
     * 这里需要传入自定义Layout的Id，则要求必须有id为tv_title的TextView，否则无任何要求
     */
    fun loadingLayoutId(@LayoutRes layoutId: Int): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_LAYOUT_ID = layoutId
        return this
    }

    /**
     * 等待时显示的文字
     * 1、等待弹窗的文字
     * 2、状态布局 - 等待时的显示的文字
     */
    fun loadingLayoutText(loadingMsg: String): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_TEXT = loadingMsg
        return this
    }

    /**
     * 页面状态：空布局
     */
    fun pageStateEmpty(state: Class<out MultiState>): GlobalConfigCreateor{
        GlobalConfig.Loading.STATE_EMPTY = state
        return this
    }

    /**
     * 页面状态：等待
     */
    fun pageStateLoading(state: Class<out MultiState>): GlobalConfigCreateor{
        GlobalConfig.Loading.STATE_LOADING = state
        return this
    }

    /**
     * 页面状态：错误页面（请求成功-但返回错误）
     */
    fun pageStateFail(state: Class<out MultiState>): GlobalConfigCreateor{
        GlobalConfig.Loading.STATE_FAIL = state
        return this
    }

    /**
     * 页面状态：失败页面
     */
    fun pageStateError(state: Class<out MultiState>): GlobalConfigCreateor{
        GlobalConfig.Loading.STATE_ERROR = state
        return this
    }

    /**
     * 显示隐藏动画时间 默认500毫秒
     */
    fun pageStateDefAlphaDuration(duration: Long): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_ALPHA_DURATION = duration
        return this
    }

    /**
     * 页面状态错误图片
     */
    fun pageStateDefErrorIcon(@DrawableRes errorIcon: Int): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_ERROR_ICON = errorIcon
        return this
    }
    /**
     * 页面状态请求失败图片
     */
    fun pageStateDefFailIcon(@DrawableRes failIcon: Int): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_FAIL_ICON = failIcon
        return this
    }
    /**
     * 页面状态 空布局时的图片
     */
    fun pageStateDefEmptyIcon(@DrawableRes emptyIcon: Int): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_EMPTY_ICON = emptyIcon
        return this
    }
    /**
     * 页面状态 请求失败时的提示信息
     */
    fun pageStateDefErrorMsg(errorMsg: String): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_ERROR_MSG = errorMsg
        return this
    }
    /**
     * 页面状态 请求错误时的提示信息
     */
    fun pageStateDefFailMsg(failMsg: String): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_FAIL_MSG = failMsg
        return this
    }
    /**
     * 页面状态 空布局时的提示信息
     */
    fun pageStateDefEmptyMsg(emptyMsg: String): GlobalConfigCreateor{
        GlobalConfig.Loading.LOADING_EMPTY_MSG = emptyMsg
        return this
    }
    /**
     * 主要是分配到具体的配置项
     * 1、GlobalConfig               -> 属性
     * 2、GlobalConfigFun            -> 方法
     * 3、GlobalConfigInitListener   -> 初始化自定义函数
     */
    fun build(){
        //初始化状态页
        GlobalConfigFun.initPageStateConfig(
            GlobalConfig.Loading.LOADING_ALPHA_DURATION,
        )
    }
}