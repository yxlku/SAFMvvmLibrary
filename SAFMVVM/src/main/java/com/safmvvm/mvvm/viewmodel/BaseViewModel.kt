package com.safmvvm.mvvm.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.kingja.loadsir.callback.Callback
import com.safmvvm.app.CheckUtil
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.ui.load.LoadingState
import com.safmvvm.ui.load.loadsir.callback.DefaultLoadingCallback
import com.safmvvm.utils.jetpack.SingleLiveEvent
import com.safmvvm.utils.jetpack.putValue

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

    internal val mUiChangeLiveData by lazy { UiChangeLiveData() }

    override fun onCleared() {
        super.onCleared()
        //livewDataBus取消监听
        LiveDataBus.removeObserve(this)
        LiveDataBus.removeStickyObserver(this)
    }

    override fun getBundle(): Bundle? = mBundle

    override fun getArgumentsIntent(): Intent? = mIntent

    /**
     * 页面状态总控：请求时自动调用此方法 - 显示等待效果
     */
    override fun showStateLoading(state: LoadingState) {
        when (state) {
            LoadingState.LOADING -> showLoadDialogIsShow(true)      //等待弹窗
            LoadingState.LOADSIR -> showLoadSir(DefaultLoadingCallback::class.java) //布局等待模式
        }
    }

    /**
     *  请求时自动调用此方法 - 显示成功效果
     */
    override fun showStateSucess() {
        //不用判断，所有等待都给我关了，防止页面显示不出来
        //关闭Loading窗体
        showLoadDialogIsShow(false)
        //关闭LoadSir
        showLoadSir(null)
    }

    // 以下是等待加载中弹窗相关的 =========================================================
    /** 等待弹窗事件：true显示，false隐藏*/
    fun showLoadDialogIsShow(isShow: Boolean){
        mUiChangeLiveData.loadDialogEvent?.putValue(isShow)
    }

    // 以下是内嵌加载中布局相关的 =========================================================
    fun showLoadSir(clz: Class<out Callback>?) {
        CheckUtil.checkLoadSirEvent(mUiChangeLiveData.loadSirEvent)
        mUiChangeLiveData.loadSirEvent?.putValue(clz)
    }
    /**
     * 发送到View层的LiveData的变量
     */
    class UiChangeLiveData {
        /** LoadSir */
        var loadSirEvent: SingleLiveEvent<Class<out Callback>?>? = null
        var loadDialogEvent: SingleLiveEvent<Boolean>? = null

        /** 初始化异步操作状态页面*/
        fun initLoadSirEvent(){
            loadSirEvent = SingleLiveEvent()
        }
        /** 初始化等待弹窗*/
        fun initLoadDialogEvent(){
            loadDialogEvent = SingleLiveEvent()
        }
    }
}
