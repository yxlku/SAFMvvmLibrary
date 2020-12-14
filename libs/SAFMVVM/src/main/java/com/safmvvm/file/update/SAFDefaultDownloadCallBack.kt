package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.CenterPopupView
import com.maning.updatelibrary.InstallUtils
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.file.update.dialog.DefaultUpdateVersionProgressDialog
import com.safmvvm.file.update.dialog.IUpdateProgressDialog
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import java.lang.Exception
import java.net.SocketException

/**
 * 默认下载回调，不想用默认的，可以在初始化的时候换个
 */
class SAFDefaultDownloadCallBack(
    val activity: Activity,
    val apkPath: String,
    var basePopupView: BasePopupView,
    var progressDialog: IUpdateProgressDialog,
    /** 是否是强制更新*/
    var isForce: Boolean,
    /** 安装时回调函数*/
    var installCallBack: InstallUtils.InstallCallBack  = ApkDownInstaller.defInstallCallBack(activity, isForce, apkPath)
) : InstallUtils.DownloadCallBack {

    override fun onStart() {
    }

    override fun onComplete(path: String) {
        //下载完成
        basePopupView.dismiss()
        progressDialog.progress(100)
        //权限检测，如果成功则直接安装，不成功则要执行弹窗提示，或者自行处理
        var installPermissionCallBack = DefInstallPermissionCallBack(activity, path, isForce, installCallBack)
        //权限检测
        InstallUtils.checkInstallPermission(activity, installPermissionCallBack)
    }

    override fun onLoading(total: Long, current: Long) {
        progressDialog.progress((current * 100 / total).toInt())
    }

    override fun onFail(e: Exception) {
        LogUtil.exception("下载失败", e)
        basePopupView.dismiss()
        if (e.message?.contains("SocketException")!! || e.message?.contains("stream was reset: CANCEL")!!) {
            ToastUtil.showShortToast("下载取消")
            return
        }
        //下载失败, 强制则不自动点击按钮不关闭弹窗
        ApkDownInstaller.tipAndToBrower(
            activity,
            "下载失败，可以跳转到系统浏览器下载",
            isForce,
            apkPath
        )
    }

    //取消下载，如果强制不能关闭
    override fun cancle() {
        basePopupView.dismiss()
        ApkDownInstaller.installCancel()
        ToastUtil.showShortToast("下载取消！")
    }

}