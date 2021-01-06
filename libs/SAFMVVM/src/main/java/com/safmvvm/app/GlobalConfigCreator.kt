package com.safmvvm.app

import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.app.globalconfig.GlobalConfigInitListener
import com.safmvvm.db.MigrationManager
import com.safmvvm.ui.toast.ToastEnumInterface
import com.safmvvm.utils.weight.TextViewDrawableEnum
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

    /**
     * 是否开启
     */
    fun appIsOpenSwipeback(isOpenSwipeback: Boolean): GlobalConfigCreator{
        GlobalConfig.App.gIsOpenSwipeback = isOpenSwipeback
        return this
    }

    /**
     * 状态栏文字是否为暗色
     */
    fun appIsStatusBarIsDark(isDark: Boolean): GlobalConfigCreator{
        GlobalConfig.App.gIsStatusBarIsDark = isDark
        return this
    }
    /************************************ Toast初始化配置 *******************************************/

    /**
     * Toast全局自定义布局，这里不设置其他地方设置也没用
     *
     * @Tip: 如果某处不想使用全局的布局，可以在调用的时候设置为isGlobalCustom = false即可
     */
    fun toastCustomLayoutId(@LayoutRes customLayoutId: Int): GlobalConfigCreator{
        GlobalConfig.Toast.gCustomLayoutId = customLayoutId
        return this
    }

    /**
     * 自定义布局中显示文字的控件Id（TextView）
     *
     * @Tip: 1、如果不设置布局toastCustomLayoutId，这个方法设置也没用
     *       2、如果不设置这个，设置图片gCustomIconId也不会显示
     */
    fun toastCustomMsgId(@IdRes msgTvId: Int): GlobalConfigCreator{
        GlobalConfig.Toast.gCustomMsgId = msgTvId
        return this
    }

    /**
     * Toast显示的位置，这个值也关系到系统默认样式弹窗的位置
     *
     * @Tip：如果不设置默认在屏幕下方
     */
    fun toastCustomToastGravity(gravity: Int): GlobalConfigCreator{
        GlobalConfig.Toast.gCustomToastGravity = gravity
        return this
    }

    /**
     * Toast 显示图标类型
     *
     * @param customToastEnum  实现类： 枚举、普通类都可以，只会获取接口中的函数对应的值，其他值无用
     */
    fun toastCustomToastEnum(customToastEnum: ToastEnumInterface): GlobalConfigCreator{
        GlobalConfig.Toast.gCustomToastEnum = customToastEnum
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
     * 是否开启屏幕适配功能，默认开启
     */
    fun autoSizeIsOpenAutoSize(isOpenAutoSize: Boolean): GlobalConfigCreator{
        GlobalConfig.AutoSize.gIsOpenAutoSize = isOpenAutoSize
        return this
    }
    /**
     * 指定设计稿的宽高
     */
    fun autoSizeDesignSize(width: Float, height: Float): GlobalConfigCreator{
        GlobalConfig.AutoSize.gDesignSize = Pair(width, height)
        return this
    }
    /**
     * 自定义控件页面或View单独设置尺寸
     */
    fun autoSizeCustomAdapter(triple: List<Triple<Class<*>, Boolean, Float>>): GlobalConfigCreator{
        GlobalConfig.AutoSize.gCustomAdapter = triple
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
    /********************************** Image初始化配置 *******************************************/
    /**
     * 全局图片占位图
     */
    fun imageResPlaceholder(@DrawableRes imageResPlaceholder: Int): GlobalConfigCreator{
        GlobalConfig.ImageView.gImageResPlaceholder = imageResPlaceholder
        return this
    }

    /**
     * 全局图片错误图
     */
    fun imageResError(@DrawableRes imageResError: Int): GlobalConfigCreator{
        GlobalConfig.ImageView.gImageResError = imageResError
        return this
    }

    /********************************** 点击事件初始化配置 *******************************************/

    /**
     * 在 xml 配置点击事件，可配置的属性如下：
     * onClickCommand 点击事件
     * isInterval 是否开启防止点击过快
     * intervalMilliseconds 防止点击过快的间隔时间，毫秒为单位
     *
     * 这里可全局设置是否开启防止点击事件过快的功能，局部可单独开启或关闭。
     *
     * 如果关闭，那么和 setOnClickListener 没啥区别
     */
    fun clickIsInterval(isClickInterval: Boolean): GlobalConfigCreator{
        GlobalConfig.Click.gIsClickInterval = isClickInterval
        return this
    }
    /**
     * 点击事件时间间隔
     */
    fun clickIntervalMilliseconds(time: Int): GlobalConfigCreator{
        GlobalConfig.Click.gClickIntervalMilliseconds = time
        return this
    }
}