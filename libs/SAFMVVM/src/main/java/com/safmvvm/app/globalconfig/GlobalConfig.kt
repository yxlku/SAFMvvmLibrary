package com.safmvvm.app.globalconfig

import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.safmvvm.R
import com.safmvvm.db.MigrationManager
import com.safmvvm.ui.load.state.DefaultEmptyPageState
import com.safmvvm.ui.load.state.DefaultErrorPageState
import com.safmvvm.ui.load.state.DefaultFailPageState
import com.safmvvm.ui.load.state.DefaultLoadingPageState
import com.zy.multistatepage.MultiState
import com.zy.multistatepage.state.SuccessState


/**
 * 全局配置
 * 这里定义，如果需要在子Module中使用配置，则需要在GlobalConfigCreateor中创建对应的方法，来公开方法
 *
 */
internal object GlobalConfig {

    const val appName = "SAFMVVM"

    object App{
        /** 子Module初始化方法*/
        var gGlobalConfigInitListener: GlobalConfigInitListener? = null
        /** 是否开启路由*/
        var gIsOpenArouter: Boolean = true
    }

    /**
     * 动画
     */
    object Anim{
        /** 是否开启页面动画*/
        var gIsOpenPageAnim: Boolean = true;
        /** 页面打开动画*/
        @AnimRes var gPageOpenIn: Int = R.anim.activity_open_in_anim
        @AnimRes var gPageOpenOut: Int = R.anim.activity_open_out_anim
        /** 页面关闭动画，默认和打开动画一样，也可以自行配置*/
        @AnimRes var gPageCloseIn: Int = gPageOpenIn
        @AnimRes var gPageCloseOut: Int = gPageOpenOut

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
    }

    /**
     * 请求可配置项
     */
    object Request{
        /** baseUrl*/
        var gBaseUrl: String = ""
        /** 必须设置*/
        var SUCCESS_CODE: String = ""
        /** 超时时间*/
        var DEFAULT_TIMEOUT: Long = 20
    }

    /**
     * 数据库配置
     */
    object DB{
        /** 数据库名称 ，如果不设置则用app名字 */
        var gDBName: String = appName
        /** 版本升级文件 */
        var gMigrationManager: MigrationManager? = null
    }

    object Update{
        /** 接口没有返回apk下载地址时，可以跳转到*/
        var gNoApkUrl = "https://android.myapp.com/"
    }

    /**
     * 弹窗配置
     */
    object Dialog{
        /** 弹窗背景是否为高斯模糊 */
        var gIsBlurBg: Boolean = true
    }

    /**
     * 等待布局或弹窗配置属性
     */
    object Loading{
        /** 自定义等待弹窗，这里需要传入自定义Layout的Id，则要求必须有id为tv_title的TextView，否则无任何要求*/
        @LayoutRes
        var LOADING_LAYOUT_ID: Int = 0

        /** 显示隐藏动画时间 默认500毫秒*/
        var LOADING_ALPHA_DURATION: Long = 500
        /** 等待时显示的文字*/
        var LOADING_TEXT: String = "loading..."

        /** 错误图片*/
        @DrawableRes
        var LOADING_ERROR_ICON: Int = R.mipmap.state_error
        /** 错误文字*/
        var LOADING_ERROR_MSG: String = "出错误了！！"
        /** 错误图片*/
        /** 失败图片*/
        @DrawableRes
        var LOADING_FAIL_ICON: Int = R.mipmap.state_error
        /** 失败文字*/
        var LOADING_FAIL_MSG: String = "没成功！！"
        /** 空图片*/
        @DrawableRes
        var LOADING_EMPTY_ICON: Int = R.mipmap.state_empty
        /** 空布局提示文字*/
        var LOADING_EMPTY_MSG: String = "这里什么都没有"


        var STATE_EMPTY: Class<out MultiState> = DefaultEmptyPageState::class.java
        var STATE_LOADING: Class<out MultiState> = DefaultLoadingPageState::class.java
        var STATE_SUCCESS: Class<out MultiState> = SuccessState::class.java
        var STATE_FAIL: Class<out MultiState> = DefaultFailPageState::class.java
        var STATE_ERROR: Class<out MultiState> = DefaultErrorPageState::class.java

    }

}