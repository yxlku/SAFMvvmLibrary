package com.safmvvm.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.safmvvm.app.BaseApp
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseLiveViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * View层接口
 */
interface IView<V: ViewDataBinding, VM: BaseLiveViewModel<out BaseModel>>: IArgumentsFromBundle {

    /**
     * 1.1 初始化databinding
     */
    fun initDatabinding(inflater: LayoutInflater, container: ViewGroup?): V
    /**
     * 1.2、初始化ViewModel
     */
    fun initViewModel()

    /**
     * 1.3、初始化界面观察者
     */
    fun initViewObservable()

    /**
     * 1.3 初始化通用的 UI 改变事件，基类应该在初始化后设为 final
     */
    fun initUiChangeLiveData()

    /**
     * 2.1、初始化外部传来的参数
     */
    fun initParams(){}

    /**
     * 2.2 初始化页面需要的数据
     */
    fun initData(){}

    /**
     * 2.3 loadSir初始化
     */
    fun initLoadSir()

    /**
     * 2.4 等待弹窗初始化
     */
    fun initLoadDialog()

    /**
     * 移除事件总线监听，避免内存泄露
     */
    fun removeLiveDataBus(owner: LifecycleOwner) {
        LiveDataBus.removeObserve(owner)
        LiveDataBus.removeStickyObserver(this)
    }

    /**
     * 初始化VM实例，如果参数类型不是
     */
    fun initViewModelInstance(viewModelStoreOwner: ViewModelStoreOwner): VM{
        val type: Type? = javaClass.genericSuperclass
        //如果没有指定泛型参数，则默认使用BaseViewModel
        var modelClass: Class<VM> = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<VM>
        }else{
            BaseLiveViewModel::class.java as Class<VM>
        }
        var vm: VM = ViewModelProvider(
            viewModelStoreOwner,
            ViewModelProvider.AndroidViewModelFactory(BaseApp.getInstance())
        ).get(modelClass)
        //让 vm 也可以直接获取到 bundle
        vm.mBundle = getBundle()
        return vm

    }
}