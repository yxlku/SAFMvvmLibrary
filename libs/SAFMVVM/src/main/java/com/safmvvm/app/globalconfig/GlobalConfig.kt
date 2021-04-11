package com.safmvvm.app.globalconfig

import android.view.Gravity
import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.hitomi.tilibrary.loader.ImageLoader
import com.hitomi.tilibrary.style.IIndexIndicator
import com.hitomi.tilibrary.style.IProgressIndicator
import com.safmvvm.R
import com.safmvvm.db.MigrationManager
import com.safmvvm.ui.load.state.DefaultEmptyPageState
import com.safmvvm.ui.load.state.DefaultErrorPageState
import com.safmvvm.ui.load.state.DefaultFailPageState
import com.safmvvm.ui.load.state.DefaultLoadingPageState
import com.safmvvm.ui.toast.DefToastEnumInterface
import com.safmvvm.ui.toast.ToastEnumInterface
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
        /** 是否开启侧滑功能*/
        var gIsOpenSwipeback: Boolean = true
        /** 默认沉浸式状态栏文字及图标颜色*/
        var gIsStatusBarIsDark: Boolean = false
    }

    /** 这里为全局配置，如果需要设置，可以通过titleBar属性来控制*/
    object TitleBar{
        /** 其次：全局：默认返回按钮*/
        @JvmField
        @DrawableRes var gTitleBarBackIcon: Int = R.drawable.bar_arrows_left_black
        /** 优先：全局：标题栏背景 如果不设置或者为null，则使用默认颜色*/
        @JvmField
        @DrawableRes var gTitleBarBg: Int? = null
    }

    /**
     * 弹窗
     */
    object Toast{
        /** 自定义Toast布局Id， 等于0时使用系统默认的*/
        @LayoutRes var gCustomLayoutId: Int = 0
        /** 自定义弹窗中msg的id，不写则会报错*/
        @IdRes var gCustomMsgId: Int = 0
        /** Toast位置*/
        var gCustomToastGravity: Int = Gravity.BOTTOM
        /** Toast文字图标显示类型*/
        var gCustomToastEnum: ToastEnumInterface = DefToastEnumInterface()
    }

    /**
     * 动画
     */
    object Anim{
        /** 是否开启页面动画*/
        var gIsOpenPageAnim: Boolean = true

        /** 页面打开动画*/
        @AnimRes var gPageOpenIn: Int = R.anim.activity_open_in_anim
        @AnimRes var gPageOpenOut: Int = R.anim.activity_open_out_anim
        /** 页面关闭动画，默认和打开动画一样，也可以自行配置*/
        @AnimRes var gPageCloseIn: Int = R.anim.activity_close_in_anim
        @AnimRes var gPageCloseOut: Int = R.anim.activity_close_out_anim
    }

    /**
     * 屏幕适配
     */
    object AutoSize{
        /**
         * 是否开启自动适配功能
         */
        var gIsOpenAutoSize: Boolean = true
        /**
         * 设计稿尺寸
         * first 宽
         * second 高
         */
        var gDesignSize: Pair<Float, Float> = Pair(750f, 1344f)
        /**
         * 自定义尺寸view
         * class<*> Activity class, Fragment class
         * Boolean  是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选一个作为基准进行适配)
         *          {@code true} 为按照宽度适配, {@code false} 为按照高度适配
         * Int      自定义规范尺寸
         */
        var gCustomAdapter: List<Triple<Class<*>, Boolean, Float>>? = null
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

    object ImageView{
        /** 占位图*/
        @DrawableRes var gImageResPlaceholder: Int? = null
        /** 加载错误时的图片*/
        @DrawableRes var gImageResError: Int? = null
        /** 加载大图所需的加载器 - 默认使用Glide的加载器*/
        var gBigPicImageLoad: ImageLoader? = null
        /** 资源加载进度指示器, 可以实现 IProgressIndicator 扩展*/
        var gBigPicProgress: IProgressIndicator? = null
        /** 指示器*/
        var gBigPicIndexIndicator: IIndexIndicator? = null
    }

    object Click{
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
        var gIsClickInterval = false

        /**i
         * 点击事件时间间隔
         */
        var gClickIntervalMilliseconds = 500
    }

}