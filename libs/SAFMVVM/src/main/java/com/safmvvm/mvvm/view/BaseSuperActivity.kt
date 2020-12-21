package com.safmvvm.mvvm.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.AnimRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.Postcard
import com.safmvvm.R
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.component.RouterUtil
import com.safmvvm.mvvm.args.IActivityResult
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseLiveViewModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.theme.StatusBarUtil
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.utils.DensityUtil
import com.safmvvm.utils.Utils
import me.jessyan.autosize.internal.CustomAdapt

/**
 * 所有Activity的基类
 */
abstract class BaseSuperActivity<V : ViewDataBinding, VM : BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null
): AppCompatActivity(), IView<V, VM>, IArgumentsFromIntent, IArgumentsFromBundle, IActivityResult{

    protected lateinit var mBinding: V
    protected lateinit var mViewModel: VM

    private lateinit var mStartActivityForResult: ActivityResultLauncher<Intent>

    var mTitleBar: TitleBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加setRequestedOrientation方法实现锁定横屏（portrait为保持竖屏，landscape为保持横屏）
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //Router注入初始化
        RouterUtil.inject(this)
        //初始化Databinding，livedata和xml可以双向绑定
        initDatabinding(layoutInflater, null)
        //初始化view
        setContentView(mBinding.root)
        //初始化标题栏，支持沉浸式
        initTitleBar()
        //初始化viewModel
        initViewModel()
        //接收的参数
        initParams()
        //livedata接收处理
        initUiChangeLiveData()
        //子类视图改变接收者
        initViewObservable()
        //初始化数据
        initData()
        //初始化等待效果
        initLoadSir()
        //初始化等待弹窗
        initLoadDialog()

        // 绑定 v 和 vm
        if (mViewModelId != null) {
            mBinding.setVariable(mViewModelId, mViewModel)
        }
        // 让 LiveData 和 xml 可以双向绑定
        mBinding.lifecycleOwner = this

    }

    /** 初始化状态栏 */
    private fun initTitleBar(){
        StatusBarUtil.init(this)
        if (mTitleBar == null) mTitleBar = StatusBarUtil.obtainTitleBar(window.decorView)
        mTitleBar?.let {
            StatusBarUtil.immersionPageView(this, it)
        }
    }

    /**
     * 初始化DataBinding
     */
    override fun initDatabinding(inflater: LayoutInflater, container: ViewGroup?): V {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        return mBinding
    }

    /**
     * 初始化ViewModel
     */
    override fun initViewModel() {
        mViewModel = initViewModelInstance(this)
        //可以让ViewModel获取到Intent中参数
        mViewModel.mIntent = getArgumentsIntent()
        // 让 vm 可以感知 v 的生命周期
        lifecycle.addObserver(mViewModel)
    }

    /**
     * 通过 [BaseLiveViewModel.startActivity] 传递 bundle，在这里可以获取
     */
    override fun getBundle(): Bundle? {
        return intent.extras
    }

    override fun getArgumentsIntent(): Intent? {
        return intent
    }

    override fun onDestroy() {
        super.onDestroy()
        // 界面销毁时移除 vm 的生命周期感知
        if (this::mViewModel.isInitialized) {
            lifecycle.removeObserver(mViewModel)
        }
        //销毁所有LiveDataBus
        removeLiveDataBus(this)
        //取消bind
        mBinding.unbind()
    }

    /**
     * <pre>
     *     // 一开始我们这么写
     *     mViewModel.liveData.observe(this, Observer { })
     *
     *     // 用这个方法可以这么写
     *     observe(mViewModel.liveData) { }
     *
     *     // 或者这么写
     *     observe(mViewModel.liveData, this::onChanged)
     *     private fun onChanged(s: String) { }
     * </pre>
     */
    fun <T> observe(liveData: LiveData<T>, onChanged: ((t: T) -> Unit)) =
        liveData.observe(this, Observer { onChanged(it) })

    /**
     * 普通开启Activity ，可传参
     */
    fun startActivity(
        clz: Class<out Activity>?,
        map: ArrayMap<String, *>? = null,
        bundle: Bundle? = null
    ) {
        startActivity(Utils.getIntentByMapOrBundle(this, clz, map, bundle))
        //动画
        startPageAnim()
    }

    /**
     * 开启Activity可回调，并附带参数
     */
    fun startActivityForResult(
        clz: Class<out Activity>?,
        map: ArrayMap<String, *>? = null,
        bundle: Bundle? = null
    ) {
        initStartActivityForResult()
        mStartActivityForResult.launch(Utils.getIntentByMapOrBundle(this, clz, map, bundle))
        //动画
        startPageAnim()
    }

    /**
     * ARouter开启Activity ，可传参
     */
    fun startActivityRouter(
        activityPath: String,
        block: (postcard: Postcard) -> Postcard
    ) {
        RouterUtil.startActivity(activityPath){
            block(it)
        }
        //动画
        startPageAnim()
    }
    /**
     * ARouter开启ActivityForResult ，可传参
     */
    fun startActivityForResultRouter(
        /** 使用startForResult功能的需要传入此值*/
        activity: Activity,
        /** 使用startForResult功能的需要传入此值*/
        requestCode: Int = 0,
        activityPath: String,
        block: (postcard: Postcard) -> Postcard
    ) {
        RouterUtil.startActivityForResult(activity, requestCode, activityPath){
            block(it)
        }
        //动画
        startPageAnim()
    }
    fun setResult(pair: Pair<Int?, Intent?>) {
        pair.first?.let { resultCode ->
            val intent = pair.second
            if (intent == null) {
                setResult(resultCode)
            } else {
                setResult(resultCode, intent)
            }
        }
    }

    override fun finish() {
        super.finish()
        //动画
        finishPageAnim()
    }

    /** 页面跳转动画：打开动画*/
    override fun startPageAnim(){
        if (GlobalConfig.Anim.gIsOpenPageAnim) {
            overridePendingTransition(GlobalConfig.Anim.gPageOpenIn, GlobalConfig.Anim.gPageOpenOut)
        }
    }
    /** 页面跳转动画： 关闭动画*/
    override fun finishPageAnim(){
        if (GlobalConfig.Anim.gIsOpenPageAnim) {
            overridePendingTransition(GlobalConfig.Anim.gPageCloseIn, GlobalConfig.Anim.gPageCloseOut)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        var data = intent ?: Intent()
        when (resultCode) {
            Activity.RESULT_OK -> {
                onActivityResultOk(data)
                if (this::mViewModel.isInitialized) {
                    //可以在ViewModel中操作返回结果
                    mViewModel.onActivityResultOk(data)
                }
            }
            Activity.RESULT_CANCELED -> {
                onActivityResultCanceled(data)
                if (this::mViewModel.isInitialized) {
                    mViewModel.onActivityResultCanceled(data)
                }
            }
            else -> {
                onActivityResult(resultCode, data)
                if (this::mViewModel.isInitialized) {
                    mViewModel.onActivityResult(resultCode, data)
                }
            }
        }
    }

    private fun initStartActivityForResult() {
        if (!this::mStartActivityForResult.isInitialized) {
            mStartActivityForResult =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    val data = it.data ?: Intent()
                    when (it.resultCode) {
                        Activity.RESULT_OK -> {
                            onActivityResultOk(data)
                            if (this::mViewModel.isInitialized) {
                                //可以在ViewModel中操作返回结果
                                mViewModel.onActivityResultOk(data)
                            }
                        }
                        Activity.RESULT_CANCELED -> {
                            onActivityResultCanceled(data)
                            if (this::mViewModel.isInitialized) {
                                mViewModel.onActivityResultCanceled(data)
                            }
                        }
                        else -> {
                            onActivityResult(it.resultCode, data)
                            if (this::mViewModel.isInitialized) {
                                mViewModel.onActivityResult(it.resultCode, data)
                            }
                        }
                    }
                }
        }
    }


}