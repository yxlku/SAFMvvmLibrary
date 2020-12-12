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
    val apkPath: String?,
    /** 是否是强制更新， 强制更新没授权则直接退出App*/
    var isForce: Boolean
): InstallUtils.InstallPermissionCallBack {

    override fun onGranted() {
        //授权了
        ApkDownInstaller.installApk(activity, apkPath)
    }

    override fun onDenied() {
        //未授权 TODO 弹窗的统一写法，样式做到统一
        XPopup.Builder(activity).asConfirm(
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
        )
        .show()
    }

}