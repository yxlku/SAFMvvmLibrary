package com.safmvvm.mvvm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel

/**
 * 所有Fragment的基类
 */
abstract class BaseSuperFragment<V: ViewDataBinding, VM: BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null
): Fragment(), IView<V, VM>{

    protected lateinit var mBinding: V
    protected lateinit var mViewModel: VM

    /** liveData接收统一处理*/
    abstract override fun initUiChangeLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        // 绑定 v 和 vm
        if (mViewModelId != null) {
            mBinding.setVariable(mViewModelId, mViewModel)
        }
        // 让 LiveData 和 xml 可以双向绑定
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化Databinding，livedata和xml可以双向绑定
        initDatabinding(layoutInflater, null)
        //初始化view
//        setContentView(mBinding.root)
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
    }


    /**
     * 初始化ViewModel
     */
    override fun initViewModel() {
        mViewModel = initViewModelInstance(this)
        // 让 vm 可以感知 v 的生命周期
        lifecycle.addObserver(mViewModel)
    }

    /**
     * 通过 setArguments 传递 bundle，在这里可以获取
     */
    override fun getBundle(): Bundle? {
        return arguments
    }

    override fun onDestroyView() {
        //取消bind
        mBinding.unbind()
        super.onDestroyView()
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

}