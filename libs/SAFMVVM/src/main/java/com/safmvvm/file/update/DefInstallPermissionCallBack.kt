package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.maning.updatelibrary.InstallUtils
import com.safmvvm.app.BaseApp
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil

/**
 * 默认安装权限检测
 */
class DefInstallPermissionCallBack(
    val activity: Activity,
    /** 下载后的apk路径*/
    val apkPath: String,
    /** 是否是强制更新， 强制更新没授权则直接退出App*/
    var isForce: Boolean,
    /** 安装时回调函数*/
    var installCallBack: InstallUtils.InstallCallBack  = ApkDownInstaller.defInstallCallBack(activity, isForce, apkPath)
): InstallUtils.InstallPermissionCallBack {

    override fun onGranted() {
        //授权了
        ApkDownInstaller.installApk(activity, isForce, apkPath, installCallBack)
    }

    override fun onDenied() {
        //未授权
        ApkDownInstaller.installNoDenied(activity, apkPath, isForce)
    }

}