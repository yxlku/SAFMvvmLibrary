package com.safmvvm.file.update

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.maning.updatelibrary.InstallUtils
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

    override fun onFail(e: Exception) {
        LogUtil.exception("下载失败", e)
        basePopupView.dismiss()
        if (e.message?.contains("SocketException")!!) {
            ToastUtil.showShortToast("下载取消")
            return
        }
        //下载失败, 强制则不自动点击按钮不关闭弹窗 TODO 弹窗的统一写法，样式做到统一
        XPopup.Builder(activity)
            .dismissOnBackPressed(!isForce) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(!isForce) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .autoDismiss(!isForce)
            .asConfirm(
            "温馨提示", "下载失败，可以跳转到系统浏览器下载",
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

    //取消下载，如果强制不能关闭
    override fun cancle() {
        basePopupView.dismiss()
        ToastUtil.showShortToast("下载取消！")
    }

}