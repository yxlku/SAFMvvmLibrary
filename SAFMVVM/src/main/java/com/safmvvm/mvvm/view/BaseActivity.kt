package com.safmvvm.mvvm.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.kingja.loadsir.core.Transport
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.safmvvm.R
import com.safmvvm.app.GlobalConfig
import com.safmvvm.mvvm.args.LoadSirUpdateMsgEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.ILoad
import com.safmvvm.utils.ResUtil
import com.safmvvm.utils.jetpack.SingleLiveEvent

/**
 * TODO 1、暂未实现基础 startActivity方法 ，我感觉可以搞个Util，不用这种基类的方式
 * TODO 传递参数不用putIntent和Bundle，封装一个LiveData
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<out BaseModel>>(
    @LayoutRes private val mLayoutId: Int,
    private val mViewModelId: Int? = null
) : BaseSuperActivity<V, VM>(mLayoutId, mViewModelId), ILoad {

    //状态：布局模式
    private lateinit var mLoadService: LoadService<*>
    //状态：弹窗模式
    private var dialogView: LoadingPopupView? = null
    /** 等待弹窗自定义布局，默认为全局配置项，如果配置项不设置则使用控件自带的布局*/
    private var  mLoadingLayoutId: Int = GlobalConfig.Loading.LOADING_LAYOUT_ID
    /** 等待等待弹窗提示信息，默认为全局配置项，如果配置项不设置，则使用控件自带的文字*/
    private var mLoadingTipText: String = GlobalConfig.Loading.LOADING_TEXT
    /**
     * 初始化LoadSir
     */
    override fun initLoadSir() {
        // 只有目标不为空的情况才有实例化的必要
        if (getLoadSirTarget() != null) {
            mLoadService = LoadSir.getDefault().register(
                getLoadSirTarget()
            ){
                //如果出现错误页等需要重新加载的页面，可在此方法中接收回调
                onLoadSirReload()
            }

            //初始化LoadSir事件
            mViewModel.mUiChangeLiveData.initLoadSirEvent()
            mViewModel.mUiChangeLiveData.loadSirEvent?.observe(this, Observer {
                if (it == null) {
                    //如果数据为空则直接不显示各种页面
                    mLoadService.showSuccess()
                    return@Observer
                }
                val callBack: Class<out Callback>? = it?.callBack
                if (callBack == null) {
                    //成功页面 （默认为啥也不显示）
                    mLoadService.showSuccess()
                } else {
                    //其他
                    if (it.isModify == true) {
                        mLoadService.setCallBack(callBack, object : Transport {
                            override fun order(context: Context?, view: View?) {
                                var iv_icon: ImageView? = view?.findViewById(R.id.iv_icon)
                                var tv_msg: TextView? = view?.findViewById(R.id.tv_msg)
                                var tv_sub_msg: TextView? = view?.findViewById(R.id.tv_sub_msg)
                                tv_msg?.setText(it.msg)
                                tv_sub_msg?.setText( it.subMsg)
                                if (it.icon != 0) {
                                    iv_icon?.setImageDrawable(ResUtil.getDrawable(it.icon))
                                }
                            }
                        })
                    }
                    mLoadService.showCallback(callBack)
                }
            })
        }
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
                XPopup.Builder(this)
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

    override fun initUiChangeLiveData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        dialogView?.destroy()
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