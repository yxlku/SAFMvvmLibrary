package com.safmvvm.mvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.Postcard
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.impl.LoadingPopupView
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.ILoad
import com.safmvvm.ui.load.state.ILoadPageState
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.Utils
import com.zy.multistatepage.MultiStatePage
import com.zy.multistatepage.OnNotifyListener

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null
) : BaseSuperFragment<V, VM>(mLayoutId, mViewModelId), ILoad{

    //状态：弹窗模式
    var dialogView: LoadingPopupView? = null
    /** 等待弹窗自定义布局，默认为全局配置项，如果配置项不设置则使用控件自带的布局*/
    protected var  mLoadingLayoutId: Int = GlobalConfig.Loading.LOADING_LAYOUT_ID
    /** 等待等待弹窗提示信息，默认为全局配置项，如果配置项不设置，则使用控件自带的文字*/
    protected var mLoadingTipText: String = GlobalConfig.Loading.LOADING_TEXT

    override fun initBigPic() {

    }
    /**
     * 初始化等待弹窗
     */
    override fun initLoadDialog() {
        //提示文字
        var tipText: String = if(mLoadingTipText.isNotEmpty()) mLoadingTipText else ""
        //布局选项 如果子Module没有配置则调用控件中的布局
        var cusLayout: Int = if(mLoadingLayoutId != 0) mLoadingLayoutId else 0
        if (dialogView == null) {
            dialogView =
                XPopup.Builder(context)
                    .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
                    .hasShadowBg(false)
                    .dismissOnBackPressed(false) //返回键不能关闭
                    .asLoading(tipText, cusLayout)
        }
        mViewModel.mUiChangeLiveData.initLoadDialogEvent()
        mViewModel.mUiChangeLiveData.loadDialogEvent?.observe(this, Observer {
            if (it == true) {
                dialogView?.show()
            } else {
                dialogView?.dismiss()
            }
        })
    }
    /**
     * 初始化LoadSir
     */
    override fun initLoadSir() {
        val multiStateContainer = MultiStatePage.bindMultiState(mBinding.root) {
            //如果出现错误页等需要重新加载的页面，可在此方法中接收回调
            onLoadSirReload()
        }
        //初始化LoadSir事件
        mViewModel.mUiChangeLiveData.initLoadSirEvent()
        mViewModel.mUiChangeLiveData.loadPageStateEvent?.observe(this, Observer {
            if (it?.state != null) {
                multiStateContainer.show(it.state, OnNotifyListener { state ->
                    if (it.isModify && state is ILoadPageState) {
                        state.setMsg(it.msg)
                        state.setSubMsg(it.subMsg)
                        state.setIcon(it.icon)
                    }
                })
            } else {
                //错误了，直接隐藏全部的遮盖
                multiStateContainer.show(GlobalConfig.Loading.STATE_SUCCESS)
            }
        })
    }
    /**
     * 事件接收
     */
    override fun initUiChangeLiveData() {
        //软键盘显示隐藏
        mViewModel.mUiChangeLiveData.initInputKeyBoard()
        mViewModel.mUiChangeLiveData.inputKeyboard!!.observe(this){
            it?.apply {
                hideOrShowInputMethod(this)
            }
        }

        //页面控制接收
        mViewModel.mUiChangeLiveData.initStartAndFinishEvent()
        // vm 可以结束界面
        LiveDataBus.observe<Unit>(
            this,
            mViewModel.mUiChangeLiveData.finishEvent!!,
            Observer {
                finish()
            },
            true
        )
        //页面回调
        LiveDataBus.observe<Triple<String, Int?, Intent?>>(
            this,
            mViewModel.mUiChangeLiveData.resultFinish!!,
            Observer {
                LiveDataBus.send(it.first, Pair(it.second, it.third))
                finish()
            },
            true
        )
//        LiveDataBus.observe<Pair<Int?, Intent?>>(
//            this,
//            mViewModel.mUiChangeLiveData.resultFinishCallback!!,
//            Observer {
//                resultFinishCallback(it)
//            },
//            true
//        )
        // vm 可以启动界面
        LiveDataBus.observe<Class<out Activity>>(
            this,
            mViewModel.mUiChangeLiveData.startActivityEvent!!,
            Observer {
                startActivity(it)
            },
            true
        )
        LiveDataBus.observe<Pair<Class<out Activity>, ArrayMap<String, *>>>(this,
            mViewModel.mUiChangeLiveData.startActivityWithMapEvent!!,
            Observer {
                startActivity(it?.first, it?.second)
            },
            true
        )
        // vm 可以启动界面，并携带 Bundle，接收方可调用 getBundle 获取
        LiveDataBus.observe<Pair<Class<out Activity>, Bundle?>>(this,
            mViewModel.mUiChangeLiveData.startActivityEventWithBundle!!,
            Observer {
                startActivity(it?.first, bundle = it?.second)
            },
            true
        )

        //Router跳转不带返回参数
        LiveDataBus.observe<String>(
            this,
            mViewModel.mUiChangeLiveData.startActivityEventRouter!!,
            Observer {
                it?.let{
                    startActivityRouter(it){
                        return@startActivityRouter it
                    }
                }
            },
            true
        )
        //Router跳转带返回参数
        LiveDataBus.observe<Pair<String, (postcard: Postcard)-> Postcard>>(
            this,
            mViewModel.mUiChangeLiveData.startActivityEventRouterPostcard!!,
            Observer {
                it?.let{
                    startActivityRouter(it.first, it.second)
                }
            },
            true
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Utils.releaseBinding(this.javaClass, BaseSuperFragment::class.java, this, "mBinding")
        dialogView?.destroy()
    }
    /**
     * 显示或隐藏键盘
     */
    fun hideOrShowInputMethod(isShow: Boolean){
        val imm: InputMethodManager = context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (isShow) {
            //显示
            imm.showSoftInput(mBinding.root, 0)
        } else {
            //隐藏键盘
            imm.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
        }
    }

    /**
     * 设置自定义等待弹窗布局
     * 1、传一个值则对应只设置一个内容
     * 2、** 两个值都不传则使用框架中默认的等待效果  **
     *
     * @param layoutId: 自定义布局Id
     * @param tipText： 自定义弹窗显示提示文字
     */
    fun setCustomDialog(@LayoutRes layoutId: Int = 0, tipText: String = ""){
        mLoadingLayoutId = if(layoutId != 0) layoutId else GlobalConfig.Loading.LOADING_LAYOUT_ID
        mLoadingTipText = if(tipText.isNotEmpty()) tipText else GlobalConfig.Loading.LOADING_TEXT
    }
}
