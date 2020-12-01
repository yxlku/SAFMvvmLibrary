package com.safmvvm.mvvm.view

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.ILoad
import com.safmvvm.utils.jetpack.SingleLiveEvent

/**
 * TODO 1、暂未实现基础 startActivity方法 ，我感觉可以搞个Util，不用这种基类的方式
 * TODO 传递参数不用putIntent和Bundle，封装一个LiveData
 * TODO 2、等待调试完基础操作后可以再添加等待效果
 * TODO 3、
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null
) : BaseSuperActivity<V, VM>(mLayoutId, mViewModelId), ILoad {

    private lateinit var mLoadService: LoadService<*>

    /**
     * 初始化LoadSir
     */
    override fun initLoadSir() {
        // 只有目标不为空的情况才有实例化的必要
        if (getLoadSirTarget() != null) {
            mLoadService = LoadSir.getDefault().register(
                getLoadSirTarget()
            ) {
                reLoad()
            }

        }
    }

    /**
     * 基类需要的LiveData操作
     */
    override fun initUiChangeLiveData() {
        /**
         * loadSir点击事件
         */
        var loadSirEvent: SingleLiveEvent<Class<out Callback>?>? = null

        fun initLoadSir(){
            loadSirEvent = SingleLiveEvent()
        }

    }


}