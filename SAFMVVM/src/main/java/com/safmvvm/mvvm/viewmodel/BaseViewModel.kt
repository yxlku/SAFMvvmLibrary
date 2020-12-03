package com.safmvvm.mvvm.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.safmvvm.app.CheckUtil
import com.safmvvm.app.GlobalConfig
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.args.LoadSirUpdateMsgEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.ui.load.LoadState
import com.safmvvm.ui.load.LoadingModel
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

    override fun showLoadPageState(
        model: LoadingModel,
        state: LoadState,
        isModify: Boolean,
        code: String,
        msg: String,
        subMsg: String,
        @DrawableRes icon: Int
    ) {
        when (model) {
            LoadingModel.LOADING -> {
                //弹窗模式 -- 只有在 loading的时候才会显示弹窗
                var isShow = state == LoadState.LOADING
                showLoadDialogIsShow(isShow)
            }
            LoadingModel.LOADSIR -> {
                var clz: Class<out Callback>?
                //页面模式
                when (state) {
                    LoadState.LOADING -> clz = GlobalConfig.Loading.CALLBACK_LOADING //布局等待状态
                    LoadState.EMPTY -> clz = GlobalConfig.Loading.CALLBACK_EMPTY //空数据状态
                    LoadState.NET_ERROR -> clz = GlobalConfig.Loading.CALLBACK_NET_ERROR //网络错误状态
                    LoadState.FAIL -> clz = GlobalConfig.Loading.CALLBACK_NET_ERROR //请求错误状态
                    else -> clz = SuccessCallback::class.java  //关闭LoadSir其他都是成功效果
                }
                //发送实体
                var entity = LoadSirUpdateMsgEntity(
                    clz,
                    isModify,
                    msg,
                    subMsg,
                    code,
                    icon
                )
                showLoadSir(entity)
            }
        }
    }

    // 以下是等待加载中弹窗相关的 =========================================================
    /** 等待弹窗事件：true显示，false隐藏*/
    fun showLoadDialogIsShow(isShow: Boolean){
        mUiChangeLiveData.loadDialogEvent?.putValue(isShow)
    }

    // 以下是内嵌加载中布局相关的 =========================================================
    fun showLoadSir(entity: LoadSirUpdateMsgEntity?) {
        CheckUtil.checkLoadSirEvent(mUiChangeLiveData.loadSirEvent)
        mUiChangeLiveData.loadSirEvent?.putValue(entity)
    }
    /**
     * 发送到View层的LiveData的变量
     */
    class UiChangeLiveData {
        /** LoadSir */
        var loadSirEvent: SingleLiveEvent<LoadSirUpdateMsgEntity?>? = null
        /** loading */
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
