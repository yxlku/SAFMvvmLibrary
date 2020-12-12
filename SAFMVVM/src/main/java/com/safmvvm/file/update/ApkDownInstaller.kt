package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.maning.updatelibrary.InstallUtils
import com.safmvvm.app.BaseApp
import com.safmvvm.file.update.dialog.DefaultUpdateVersionProgressDialog
import com.safmvvm.utils.LogUtil

/**
 * Apk下载及安装
 */
object ApkDownInstaller {

    fun apkDownload(activity: Activity, apkPath: String, isForce: Boolean){
        var progressDialog = DefaultUpdateVersionProgressDialog(activity, isForce) {
            //等待窗上，取消时将取消下载功能
            if (InstallUtils.isDownloading()) InstallUtils.cancleDownload()
        }
        //TODO 全局配置，也有默认
        var popView = XPopup.Builder(activity)
            .hasBlurBg(true) //高斯模糊
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .asCustom(progressDialog)
            .show()

        var downCallBack = SAFDefaultDownloadCallBack(activity, apkPath, popView, progressDialog, isForce) //TODO 全局配置，也有默认
        InstallUtils.with(BaseApp.getInstance())
            .setApkUrl(apkPath)
            .setCallBack(downCallBack)
            .startDownload()
    }

    fun installApk(activity: Activity, apkPath: String?){
        InstallUtils.installAPK(
            activity,
            apkPath,
            object : InstallUtils.InstallCallBack {
                override fun onSuccess() {
                    //onSuccess：表示系统的安装界面被打开
                    //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                    //强制更新的时候，这里要重新显示，提示更新按钮
                    LogUtil.d("安装成功！")
                }

                override fun onFail(e: Exception) {
                    //安装失败
                    LogUtil.exception("安装失败！", e)
                }
            })
    }


}