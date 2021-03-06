package com.safmvvm.mvvm.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.MainThread
import androidx.collection.ArrayMap
import androidx.lifecycle.AndroidViewModel
import com.alibaba.android.arouter.facade.Postcard
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.args.IArgumentsFromBundle
import com.safmvvm.mvvm.args.IArgumentsFromIntent
import com.safmvvm.mvvm.args.LoadSirUpdateMsgEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.ui.load.LoadState
import com.safmvvm.ui.load.LoadingModel
import com.zy.multistatepage.MultiState
import java.util.*
import kotlin.collections.ArrayList

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
abstract class BaseLiveViewModel<M: BaseModel>(app: Application): AndroidViewModel(app)
    , IArgumentsFromBundle, IArgumentsFromIntent {

    internal var mBundle: Bundle? = null
    internal var mIntent: Intent? = null

    val mUiChangeLiveData by lazy { UiChangeLiveData() }

    override fun onCleared() {
        super.onCleared()
        //livewDataBus取消监听
        LiveDataBus.removeObserve(this)
        LiveDataBus.removeStickyObserver(this)
    }

    override fun getBundle(): Bundle? = mBundle

    override fun getArgumentsIntent(): Intent? = mIntent

    /**
     * 控制页面状态功能
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
    ) {
        when (model) {
            LoadingModel.LOADING -> {
                //弹窗模式 -- 只有在 loading的时候才会显示弹窗
                var isShow = state == LoadState.LOADING
                showLoadDialogIsShow(isShow)
            }
            LoadingModel.LOAD_PAGESATE -> {
                var multiState: Class<out MultiState>
                //页面模式
                when (state) {
                    LoadState.LOADING -> multiState = GlobalConfig.Loading.STATE_LOADING //布局等待状态
                    LoadState.EMPTY -> multiState = GlobalConfig.Loading.STATE_EMPTY  //空数据状态
                    LoadState.ERROR -> multiState = GlobalConfig.Loading.STATE_ERROR  //网络错误状态
                    LoadState.FAIL -> multiState = GlobalConfig.Loading.STATE_FAIL  //请求错误状态
                    else -> multiState = GlobalConfig.Loading.STATE_SUCCESS   //关闭LoadSir其他都是成功效果
                }
                //发送实体
                var entity = LoadSirUpdateMsgEntity(multiState, isModify, msg, subMsg, code, icon)
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
    /** 显示制定的StatePage */
    fun showLoadSir(entity: LoadSirUpdateMsgEntity?) {
        mUiChangeLiveData.loadPageStateEvent?.putValue(entity)
    }
    /** 大图展示*/
    fun showBigPic(view: View?, position: Int, imgPaths: List<String>){
        mUiChangeLiveData.bigPicEvent?.putValue(Triple(view, position, imgPaths))
    }

    /**
     * 控制键盘是否显示
     * @param isShow true显示键盘，false隐藏键盘
     */
    fun controlInputKeyboard(isShow: Boolean){
        mUiChangeLiveData.inputKeyboard?.putValue(isShow)
    }
    // 以下是原生界面开启和结束相关的 =========================================================
    @MainThread
    fun resultFinish(tag: String, resultCode: Int, data: Intent? = null) {
        LiveDataBus.send(mUiChangeLiveData.resultFinish!!, Triple(tag, resultCode, data))
    }

    @MainThread
    fun finish() {
        LiveDataBus.send(mUiChangeLiveData.finishEvent!!, Unit)
    }

    fun startActivity(clazz: Class<out Activity>) {
        LiveDataBus.send(mUiChangeLiveData.startActivityEvent!!, clazz)
    }

    fun startActivity(clazz: Class<out Activity>, map: ArrayMap<String, *>) {
        LiveDataBus.send(mUiChangeLiveData.startActivityWithMapEvent!!, Pair(clazz, map))
    }

    fun startActivity(clazz: Class<out Activity>, bundle: Bundle?) {
        LiveDataBus.send(mUiChangeLiveData.startActivityEventWithBundle!!, Pair(clazz, bundle))
    }

    // Router方式跳转===================================================================================
    fun startActivityRouter(routerPath: String) {
        LiveDataBus.send(mUiChangeLiveData.startActivityEventRouter!!, routerPath)
    }
    fun startActivityRouterPostcard(routerPath: String, block: (postcard: Postcard)->Postcard) {
        LiveDataBus.send(mUiChangeLiveData.startActivityEventRouterPostcard!!, Pair(routerPath, block))
    }

    // ===================================================================================

    /**
     * 通用的 Ui 改变变量
     */
    /** */
    /**
     * 发送到View层的LiveData的变量
     */
    class UiChangeLiveData {
        /** LoadPageState */
        var loadPageStateEvent: SingleLiveEvent<LoadSirUpdateMsgEntity?> = SingleLiveEvent()
        /** loading */
        var loadDialogEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
        /** 大图展示*/
        var bigPicEvent: SingleLiveEvent<Triple<View?, Int, List<String>>> = SingleLiveEvent()
        /** 键盘显示隐藏*/
        var inputKeyboard: SingleLiveEvent<Boolean> = SingleLiveEvent()

        /** 打开Activity页面*/
        var startActivityEvent: String? = null
        /** 打开Activity页面 传递Map*/
        var startActivityWithMapEvent: String? = null
        /** 打开Activity页面 传递Bundle*/
        var startActivityEventWithBundle: String? = null

        /** Router打开Activity页面*/
        var startActivityEventRouter: String? = null
        /** Router打开Activity页面 传递自定义参数*/
        var startActivityEventRouterPostcard: String? = null

        /** 页面关闭并带有回调功能*/
        var resultFinish: String? = null
        /** 关闭Activity*/
        var finishEvent: String? = null

        /** 初始化异步操作状态页面*/
        fun initLoadSirEvent(){
        }
        /** 初始化等待弹窗*/
        fun initLoadDialogEvent(){
        }
        /** 大图展示*/
        fun initBigPicEvent(){
        }
        /** 初始化键盘控制*/
        fun initInputKeyBoard(){
        }

        /** 原生调用前初始化*/
        fun initStartAndFinishEvent() {
            startActivityEvent = UUID.randomUUID().toString()
            startActivityWithMapEvent = UUID.randomUUID().toString()
            startActivityEventWithBundle = UUID.randomUUID().toString()

            startActivityEventRouter = UUID.randomUUID().toString()
            startActivityEventRouterPostcard = UUID.randomUUID().toString()

            finishEvent = UUID.randomUUID().toString()
            resultFinish = UUID.randomUUID().toString()
        }

    }
}
