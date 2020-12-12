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
        var progressDialog = DefaultUpdateVersionProgressDialog(activity, isForce) //TODO 全局配置，也有默认
        var popView = XPopup.Builder(activity)
            .hasBlurBg(true) //高斯模糊
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .asCustom(progressDialog)
            .show()

        InstallUtils.with(BaseApp.getInstance())
            .setApkUrl(apkPath)
            .setCallBack(SAFDefaultDownloadCallBack(activity, popView, progressDialog, isForce)) //TODO
            .startDownload()
    }

    fun installApk(activity: Activity, apkPath: String?){
        InstallUtils.installAPK(
            activity,
            apkPath,
            object : InstallUtils.InstallCallBack {
                override fun onSuccess() {
                    //安装成功
                    LogUtil.d("安装成功！")
                }

                override fun onFail(e: Exception) {
                    //安装失败
                    LogUtil.exception("安装失败！", e)
                }
            })
    }


}