package com.safmvvm.mvvm.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.model.BaseModel

/**
 *
 * 对View层的感知
 *  1、liveData
 *  2、事件总线
 *  3、databinding
 *
 * 继承带有
 *  1、协程
 *  2、Model初始化操作
 */
abstract class BaseViewModel<M: BaseModel>(app: Application): BaseSuperViewModel<M>(app)
    , IArgumentsFromBundle, IArgumentsFromIntent {

    internal var mBundle: Bundle? = null
    internal var mIntent: Intent? = null

    override fun onCleared() {
        super.onCleared()
        //livewDataBus取消监听
        LiveDataBus.removeObserve(this)
        LiveDataBus.removeStickyObserver(this)
    }

    override fun getBundle(): Bundle? = mBundle

    override fun getArgumentsIntent(): Intent? = mIntent
}