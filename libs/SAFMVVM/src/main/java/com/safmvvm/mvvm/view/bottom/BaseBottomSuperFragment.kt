package com.safmvvm.mvvm.view.bottom

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.collection.ArrayMap
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.Postcard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.safmvvm.R
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.component.RouterUtil
import com.safmvvm.mvvm.args.IResultFinishCallback
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.view.IView
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.theme.StatusBarUtil
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.utils.Utils
import me.jessyan.autosize.AutoSizeCompat
import me.jessyan.autosize.utils.AutoSizeUtils
import me.jessyan.autosize.utils.ScreenUtils

/**
 * 所有Fragment的基类
 */
abstract class BaseBottomSuperFragment<V: ViewDataBinding, VM: BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null,
    /** 共享使用Activity中的VM*/
    private val sharedViewModel: Boolean = false
): BottomSheetDialogFragment(), IView<V, VM>, IResultFinishCallback{

    protected lateinit var mBinding: V
    protected lateinit var mViewModel: VM

    var mTitleBar: TitleBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransBottomSheetDialogStyle)
    }

    /**
     * 设置高度
     */
    fun setHeight(pHeight: Float){
        //设置宽度
        mBinding.root.layoutParams.apply {
            height = AutoSizeUtils.mm2px(context, pHeight)
        }
    }

    /**
     * 设置最大高度 - 比例
     */
    fun setMaxHeight(maxHeight: Float){
        //设置高度
        mBinding.root.layoutParams.apply {
            height = (maxHeight * ScreenUtils.getScreenSize(context)[1]).toInt()
        }
    }

    /**
     * 设置高度
     */
    fun setHeight(pHeight: Int){
        //设置宽度
        mBinding.root.layoutParams.apply {
            height = pHeight
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        mBinding = initDatabinding(LayoutInflater.from(context), null)
        //Router注入初始化
        RouterUtil.inject(this)
//        //默认最大高度为屏幕一半
//        setMaxHeight(1F)

        //初始化viewModel
        initViewModel()
        //沉浸式标题栏
        initTitleBar(mBinding.root)
        //接收的参数
        initParams()
        //livedata接收处理
        initUiChangeLiveData()
        //子类视图改变接收者
        initViewObservable()
        //初始化数据
        initData()
        //等待弹窗初始化
        initLoadDialog()
        dialog.setContentView(mBinding.root)
    }
    override fun initDatabinding(inflater: LayoutInflater, container: ViewGroup?): V =
        DataBindingUtil.inflate(inflater, mLayoutId, container, false)

    /**
     * 初始化ViewModel
     */
    override fun initViewModel() {
        mViewModel = if(sharedViewModel) {
            initViewModelInstance(requireActivity())
        }else{
            initViewModelInstance(this)
        }
        // 让 vm 可以感知 v 的生命周期
        lifecycle.addObserver(mViewModel)
        // 绑定 v 和 vm
        if (mViewModelId != null) {
            mBinding.setVariable(mViewModelId, mViewModel)
        }
        // 让 LiveData 和 xml 可以双向绑定
        mBinding.lifecycleOwner = this
    }

    /** 初始化状态栏 */
    private fun initTitleBar(view: View){
//        StatusBarUtil.init(this, GlobalConfig.App.gIsStatusBarIsDark)
        if (mTitleBar == null) mTitleBar = StatusBarUtil.obtainTitleBar(view)
        mTitleBar?.let {
//            StatusBarUtil.immersionPageView(this, it)
            it.setOnTitleBarListener(object : OnTitleBarListener {
                override fun onLeftClick(v: View?) {
                    if (titleBackFinish()) dismiss()
                }

                override fun onTitleClick(v: View?) {
                }

                override fun onRightClick(v: View?) {
                }
            })
        }
    }


    /**
     * 通过 setArguments 传递 bundle，在这里可以获取
     */
    override fun getBundle(): Bundle? {
        return arguments
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Utils.releaseBinding(this.javaClass, BaseBottomSuperFragment::class.java, this, "mBinding")
    }

    override fun onDestroy() {
        super.onDestroy()
        // 界面销毁时移除 vm 的生命周期感知
        if (this::mViewModel.isInitialized) {
            lifecycle.removeObserver(mViewModel)
        }
        //销毁所有LiveDataBus
        removeLiveDataBus(this)
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


    /** 页面跳转动画：打开动画*/
    override fun startPageAnim(){
        if (GlobalConfig.Anim.gIsOpenPageAnim) {
            activity?.overridePendingTransition(GlobalConfig.Anim.gPageOpenIn, GlobalConfig.Anim.gPageOpenOut)
        }
    }
    /** 页面跳转动画： 关闭动画*/
    override fun finishPageAnim(){
        if (GlobalConfig.Anim.gIsOpenPageAnim) {
            activity?.overridePendingTransition(GlobalConfig.Anim.gPageCloseIn, GlobalConfig.Anim.gPageCloseOut)
        }
    }

    fun finish(){
        activity?.finish()
        finishPageAnim()
    }

    fun startActivity(
        clz: Class<out Activity>?,
        map: ArrayMap<String, *>? = null,
        bundle: Bundle? = null
    ) {
        startActivity(Utils.getIntentByMapOrBundle(context, clz, map, bundle))
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

    fun show(tag: String = ""){
        show(childFragmentManager, tag)
    }
    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
//        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())//如果没有自定义需求用这个方法
    }

}