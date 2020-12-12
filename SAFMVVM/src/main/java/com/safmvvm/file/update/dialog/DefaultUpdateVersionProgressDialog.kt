package com.safmvvm.file.update.dialog

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.liulishuo.magicprogresswidget.MagicProgressCircle
import com.lxj.xpopup.core.CenterPopupView
import com.safmvvm.R


/**
 * 默认更新等待弹窗
 */
class DefaultUpdateVersionProgressDialog(
    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    var context: Activity,
    /** 是否强制更新*/
    var isForce: Boolean
): CenterPopupView(context), IUpdateProgressDialog {
    /** 等待进度条*/
    var mpc_progress: MagicProgressCircle? = null
    /** 等待文字*/
    var tv_cancel: TextView? = null
    /** 进度*/
    var tv_progress: TextView? = null

    // 返回自定义弹窗的布局
    override fun getImplLayoutId(): Int {
        return R.layout.dialog_update_progress
    }

    override fun onCreate() {
        super.onCreate()
        mpc_progress = findViewById(R.id.mpc_progress)
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_progress = findViewById(R.id.tv_progress)
        tv_cancel?.setOnClickListener {
            //TODO 取消的时候要取消下载
            dismiss()
        }
        //是否强制更新
        isForce()
    }

    /**
     * 更新进度 ，下载处调用
     */
    override fun progress(progress: Int) {
        mpc_progress?.setSmoothPercent(progress / 100f)
        tv_progress?.text = progress.toString()
    }

    /**
     * 下载成功
     */
    override fun success() {
        //成功后显示安装 tip -- 如果点击取消则杀死App
    }

    /**
     * 下载错误
     */
    override fun error() {
        //错误时强制也可以进入app -- 防止不能使用
    }

    /**
     * 是否是强制更新
     * 强制更新：
     * 1、物理返回不能使用
     * 2、屏幕点击不能取消弹窗
     */
    fun isForce() {
        if (isForce) {
            //强制
            // 不显示取消按钮
            tv_cancel?.visibility = View.GONE
        }else{
            //非强制
            //限制取消按钮
            tv_cancel?.visibility = View.VISIBLE
            //物理按钮能取消
            //屏幕点击能取消
        }
    }
}