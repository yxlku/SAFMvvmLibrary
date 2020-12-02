package com.safmvvm.mvvm.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.DefaultLifecycleObserver
import com.safmvvm.ui.load.LoadState
import com.safmvvm.ui.load.LoadingModel

/**
 * ViewModel 层，让 vm 可以感知 v 的生命周期
 */
interface IViewModel : DefaultLifecycleObserver {

    /**
     * 页面请求状态更新
     */
    fun showLoadPageState(
        model: LoadingModel,
        state: LoadState,
        /** 是否需要修改，false下面传的值不发生变化*/
        isModify: Boolean = false,
        code: String = "",
        msg: String = "",
        subMsg: String = "",
        @DrawableRes icon: Int = 0
    )


}