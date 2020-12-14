package com.safmvvm.file.update.dialog

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.liulishuo.magicprogresswidget.MagicProgressCircle
import com.lxj.xpopup.core.CenterPopupView
import com.safmvvm.R
import com.safmvvm.file.update.ApkDownInstaller


/**
 * 默认更新等待弹窗
 *
 * 只管下载进度就行了
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
            dismiss()
        }
        //是否强制更新
        if (isForce) {
            //强制 不显示取消按钮
            tv_cancel?.visibility = View.GONE
        }else{
            //非强制 限制取消按钮
            tv_cancel?.visibility = View.VISIBLE
        }
    }

    override fun dismiss() {
        super.dismiss()
        ApkDownInstaller.installCancel()
    }

    /**
     * 更新进度 ，下载处调用
     */
    override fun progress(progress: Int) {
        mpc_progress?.setSmoothPercent(progress / 100f)
        tv_progress?.text = progress.toString()
    }

}