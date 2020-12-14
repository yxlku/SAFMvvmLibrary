package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.maning.updatelibrary.InstallUtils
import com.safmvvm.app.BaseApp
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.file.update.dialog.DefaultUpdateVersionProgressDialog
import com.safmvvm.file.update.dialog.IUpdateProgressDialog
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil

/**
 * Apk下载及安装
 */
object ApkDownInstaller {
    /**
     * 处理后的apk地址
     */
    fun dealApkPath(apkPath: String?): String{
        if(apkPath == null || apkPath?.isBlank()){
            return GlobalConfig.Update.gNoApkUrl
        }else{
            return apkPath
        }
    }
    /**
     * 应用升级
     * 1、apk下载功能
     * 2、下载成功后安装操作
     */
    fun apkDownload(
        activity: Activity,
        /** apk下载路径, 不传时将会使用全局配置地址*/
        apkPath: String?,
        /** 是否是强制更新*/
        isForce: Boolean,
        /** 如果只实现IUpdateProgressDialog或者BasePopupView，都只会调用默认的下载样式，只有同时继承或实现才可以成功使用自定义下载样式*/
        progressDialog: IUpdateProgressDialog = DefaultUpdateVersionProgressDialog(activity, isForce),
        /** 安装时回调函数*/
        installCallBack: InstallUtils.InstallCallBack = defInstallCallBack(activity, isForce, apkPath)
    ){
        if(apkPath == null || apkPath?.isBlank()){
            tipAndToBrower(activity, "请下载更新最新版本", isForce, GlobalConfig.Update.gNoApkUrl)
            return
        }

        var pb = if(progressDialog is BasePopupView) progressDialog else DefaultUpdateVersionProgressDialog(activity, isForce)
        //TODO 弹窗全局配置，也有默认
        var popView = XPopup.Builder(activity)
            .hasBlurBg(GlobalConfig.Dialog.gIsBlurBg) //高斯模糊
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .asCustom(pb)
            .show()

        //下载apk回调
        var downCallBack = SAFDefaultDownloadCallBack(activity, apkPath, popView, pb, isForce, installCallBack)
        InstallUtils.with(BaseApp.getInstance())
            .setApkUrl(apkPath)
            .setCallBack(downCallBack)
            .startDownload()
    }

    /**
     * 安装apk
     *
     * @apkPath apk安装路径
     */
    fun installApk(
        activity: Activity,
        isForce: Boolean,
        apkPath: String,
        installCallBack: InstallUtils.InstallCallBack = defInstallCallBack(activity, isForce, apkPath)
    ){
        InstallUtils.installAPK(
            activity,
            apkPath,
            installCallBack)
    }

    fun defInstallCallBack(activity: Activity, isForce: Boolean, apkPath: String?) = object :InstallUtils.InstallCallBack {
        override fun onSuccess() {
            //onSuccess：表示系统的安装界面被打开
            //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
            //强制更新的时候，这里要重新显示，提示更新按钮
            //TODO 这里要抛出接口，方便强制安装时的调用
            LogUtil.d("安装成功！")
        }

        override fun onFail(e: Exception) {
            //安装失败
            LogUtil.exception("安装失败！", e)
            tipAndToBrower(activity, "安装失败，需要到浏览器中下载安装", isForce, apkPath+"")
        }
    }

    /**
     * 取消下载安装
     */
    fun installCancel(){
        if (InstallUtils.isDownloading()) InstallUtils.cancleDownload()
    }

    /**
     * 未授权
     *
     * 如果是强制更新的时候，必须去授权（没授权取消按钮不会显示，当然也不能关闭弹窗）
     */
    fun installNoDenied(activity: Activity, apkPath: String, isForce: Boolean){
        XPopup.Builder(activity)
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .autoDismiss(!isForce)
            .asConfirm(
            "温馨提示", "必须授权才能安装APK，请设置允许安装",
            "取消", "确定",
            object : OnConfirmListener {
                override fun onConfirm() {
                    InstallUtils.openInstallPermissionSetting(activity, DefInstallPermissionCallBack(activity, apkPath, isForce))
                }
            },
            object : OnCancelListener {
                override fun onCancel() {
                    //强制更新不会显示此按钮
                    ToastUtil.showShortToast("取消将不能更新！")
                }

            },
            isForce
        ).show()
    }

    /**
     * 提示并跳转到系统浏览器
     */
    fun tipAndToBrower(
        activity: Activity,
        /** 提示信息*/
        tipContent: String = "下载失败，可以跳转到系统浏览器下载",
        /** 是否为强制更新，强制更新跳转到系统浏览器则不会关闭提示弹窗*/
        isForce: Boolean,
        /** 跳转路径、可以为apk下载路径、也可以为下载页面*/
        apkPath: String
    ) {
        //下载失败, 强制则不自动点击按钮不关闭弹窗 TODO 弹窗的统一写法，样式做到统一
        XPopup.Builder(activity)
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .autoDismiss(!isForce)
            .asConfirm(
                "温馨提示", tipContent,
                "取消", "确定",
                object : OnConfirmListener {
                    override fun onConfirm() {
                        InstallUtils.installAPKWithBrower(activity, apkPath)
                    }
                },
                object : OnCancelListener {
                    override fun onCancel() {
                    }
                },
                isForce //强制则不显示取消按钮，app内失败了，就要去浏览器下载
            )
            .show()
    }

}