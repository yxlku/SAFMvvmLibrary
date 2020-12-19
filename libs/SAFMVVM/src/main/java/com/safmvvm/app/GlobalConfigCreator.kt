package com.safmvvm.app

import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.app.globalconfig.GlobalConfigFun
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.db.MigrationManager
import com.safmvvm.file.update.dialog.IUpdateProgressDialog
import com.zy.multistatepage.MultiState

/**
 * 1、统一调用位置
 * 2、统一分配调用对象，不用子Module来想给谁分配
 * 3、赋值的时候可以增加一些限制
 *
        fun method(): GlobalConfigCreateor{
            。。。。
            return this
        }
 */
class GlobalConfigCreator {

    /************************************ App全局性请求初始化配置 *******************************************/
    fun setGlobalConfigInitListener(listener: GlobalConfigInitListener): GlobalConfigCreator{
        GlobalConfig.App.gGlobalConfigInitListener = listener
        return this
    }

    /**
     * 设置是否使用ARouter，如果项目中使用了Arouter，但是这里设置为false，则调用ARouter时会崩溃
     */
    fun appIsOpenRouter(isOpenRouter: Boolean): GlobalConfigCreator{
        GlobalConfig.App.gIsOpenArouter = isOpenRouter
        return this
    }

    /************************************ Anim请求初始化配置 *******************************************/
    /**
     * 是否使用跳转动画
     */
    fun animIsOpen(isOpenPageAnim: Boolean): GlobalConfigCreator{
        GlobalConfig.Anim.gIsOpenPageAnim = isOpenPageAnim
        return this
    }
    /**
     * 页面开启动画 ： 每次配置后，需要卸载app才会生效
     */
    fun animPageOpenIn(@AnimRes pageOpenIn: Int): GlobalConfigCreator{
        GlobalConfig.Anim.gPageOpenIn = pageOpenIn
        return this
    }
    /**
     * 页面开启离开动画：每次配置后，需要卸载app才会生效
     */
    fun animPageOpenOut(@AnimRes pageOpenOut: Int): GlobalConfigCreator{
        GlobalConfig.Anim.gPageOpenOut = pageOpenOut
        return this
    }
    /**
     * 页面关闭进入动画：每次配置后，需要卸载app才会生效
     */
    fun animPageCloseIn(@AnimRes pageCloseIn: Int): GlobalConfigCreator{
        GlobalConfig.Anim.gPageCloseIn = pageCloseIn
        return this
    }
    /**
     * 页面关闭离开动画：每次配置后，需要卸载app才会生效
     */
    fun animPageCloseOut(@AnimRes pageCloseOut: Int): GlobalConfigCreator{
        GlobalConfig.Anim.gPageCloseOut = pageCloseOut
        return this
    }

    /************************************ Request请求初始化配置 *******************************************/
    /**
     * 全局统一BaseUrl
     */
    fun requestBaseUrl(baseUrl: String): GlobalConfigCreator{
        GlobalConfig.Request.gBaseUrl = baseUrl
        return this
    }

    /**
     * 默认成功状态码
     */
    fun requestSuccessCode(successCode: String): GlobalConfigCreator{
        GlobalConfig.Request.SUCCESS_CODE = successCode
        return this
    }

    /**
     * 默认请求超时时间
     * 单位：s秒
     */
    fun requestTimeOut(timeout: Long): GlobalConfigCreator{
        GlobalConfig.Request.DEFAULT_TIMEOUT = timeout
        return this
    }
    /************************************ 日志初始化配置 *******************************************/
    /**
     * 日志是否开启
     */
    fun logIsOpen(isOpen: Boolean): GlobalConfigCreator{
        GlobalConfig.Log.gIsOpenLog = isOpen
        return this
    }
    /**
     * 全局日志Log的Tag
     */
    fun logTag(tag: String): GlobalConfigCreator{
        GlobalConfig.Log.gLogTag = tag
        return this
    }
    /**
     * 日志是否保存到文件中
     */
    fun logIsSaveLogToFile(isSaveLogToFile: Boolean): GlobalConfigCreator{
        GlobalConfig.Log.gIsSaveLogToFile = isSaveLogToFile
        return this
    }

    /********************************** 数据库初始化配置 *******************************************/
    /**
     * 1、设置数据库名称， 一个App只有一个DB，别整那么多数据库，操作表就行
     *
     * 2、如果实在想多个数据库就直接用RoomUtil
     */
    fun dbName(dbName: String): GlobalConfigCreator{
        GlobalConfig.DB.gDBName = dbName
        return this
    }

    /**
     * 管理数据库升级版本
     */
    fun dbConfigMigrationManager(migrationManager: MigrationManager): GlobalConfigCreator{
        GlobalConfig.DB.gMigrationManager = migrationManager
        return this
    }

    /********************************** 版本更新初始化配置 *******************************************/
    /**
     * 版本更新，如果接口没有返回地址时，如果不设置此字段，将会跳转到应用宝网页（可以将此字段作为应急操作，防止不能更新）
     */
    fun updateNoApkUrl(noApkUrl: String): GlobalConfigCreator{
        GlobalConfig.Update.gNoApkUrl = noApkUrl
        return this
    }

    /********************************** 请求状态时初始化配置 *******************************************/
    /**
     * 全局统一等待弹窗布局ID
     *
     * 这里需要传入自定义Layout的Id，则要求必须有id为tv_title的TextView，否则无任何要求
     */
    fun loadingLayoutId(@LayoutRes layoutId: Int): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_LAYOUT_ID = layoutId
        return this
    }

    /**
     * 等待时显示的文字
     * 1、等待弹窗的文字
     * 2、状态布局 - 等待时的显示的文字
     */
    fun loadingLayoutText(loadingMsg: String): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_TEXT = loadingMsg
        return this
    }

    /**
     * 页面状态：空布局
     */
    fun pageStateEmpty(state: Class<out MultiState>): GlobalConfigCreator{
        GlobalConfig.Loading.STATE_EMPTY = state
        return this
    }

    /**
     * 页面状态：等待
     */
    fun pageStateLoading(state: Class<out MultiState>): GlobalConfigCreator{
        GlobalConfig.Loading.STATE_LOADING = state
        return this
    }

    /**
     * 页面状态：错误页面（请求成功-但返回错误）
     */
    fun pageStateFail(state: Class<out MultiState>): GlobalConfigCreator{
        GlobalConfig.Loading.STATE_FAIL = state
        return this
    }

    /**
     * 页面状态：失败页面
     */
    fun pageStateError(state: Class<out MultiState>): GlobalConfigCreator{
        GlobalConfig.Loading.STATE_ERROR = state
        return this
    }

    /**
     * 显示隐藏动画时间 默认500毫秒
     */
    fun pageStateDefAlphaDuration(duration: Long): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_ALPHA_DURATION = duration
        return this
    }

    /**
     * 页面状态错误图片
     */
    fun pageStateDefErrorIcon(@DrawableRes errorIcon: Int): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_ERROR_ICON = errorIcon
        return this
    }
    /**
     * 页面状态请求失败图片
     */
    fun pageStateDefFailIcon(@DrawableRes failIcon: Int): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_FAIL_ICON = failIcon
        return this
    }
    /**
     * 页面状态 空布局时的图片
     */
    fun pageStateDefEmptyIcon(@DrawableRes emptyIcon: Int): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_EMPTY_ICON = emptyIcon
        return this
    }
    /**
     * 页面状态 请求失败时的提示信息
     */
    fun pageStateDefErrorMsg(errorMsg: String): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_ERROR_MSG = errorMsg
        return this
    }
    /**
     * 页面状态 请求错误时的提示信息
     */
    fun pageStateDefFailMsg(failMsg: String): GlobalConfigCreator{
        GlobalConfig.Loading.LOADING_FAIL_MSG = failMsg
        return this
    }
    /**
     * 页面状态 空布局时的提示信息
     */
    fun pageStateDefEmptyMsg(emptyMsg: String): GlobalConfigCreator{
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