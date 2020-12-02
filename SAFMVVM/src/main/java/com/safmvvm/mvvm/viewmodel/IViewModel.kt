package com.safmvvm.mvvm.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import com.safmvvm.ui.load.LoadingState

/**
 * ViewModel 层，让 vm 可以感知 v 的生命周期
 */
interface IViewModel: DefaultLifecycleObserver{

    /**
     * 网络请求等待效果
     * @param state 通过状态来判断
     */
    fun showStateLoading(state: LoadingState)

    /**
     * 成功后的操作，通常是隐藏所有等待效果，所以也不传入任何值了
     */
    fun showStateSucess(){}

    /**
     * 空布局
     * @param state 通过状态来判断
     */
    fun showStateEmpty(state: LoadingState, tipMsg: String){}


    /**
     * 错误页面处理
     * @param state 通过状态来判断
     */
    fun showStateFail(state: LoadingState, failMsg: String){}

}