package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.core.BasePopupView
import com.maning.updatelibrary.InstallUtils
import com.safmvvm.file.update.dialog.IUpdateProgressDialog
import com.safmvvm.utils.ToastUtil
import java.lang.Exception

/**
 * 默认下载回调，不想用默认的，可以在初始化的时候换个
 */
class SAFDefaultDownloadCallBack(
    val activity: Activity,
    var basePopupView: BasePopupView,
    var progressDialog: IUpdateProgressDialog,
    /** 是否是强制更新*/
    var isForce: Boolean
) : InstallUtils.DownloadCallBack {

    override fun onStart() {
    }

    override fun onComplete(path: String?) {
        //下载完成
        basePopupView.dismiss()
        progressDialog.progress(100)
        //权限检测，如果成功则直接安装，不成功则要执行弹窗提示，或者自行处理
        var installPermissionCallBack = DefInstallPermissionCallBack(activity, path, isForce) //TODO
        //权限检测
        InstallUtils.checkInstallPermission(activity, installPermissionCallBack)
    }

    override fun onLoading(total: Long, current: Long) {
        progressDialog.progress((current * 100 / total).toInt())
    }

    override fun onFail(e: Exception?) {
        //TODO 是否可以直接跳到到浏览器去下载
        ToastUtil.showShortToast("下载失败：" + e?.message)
    }

    override fun cancle() {
        ToastUtil.showShortToast("下载取消！")
    }

}