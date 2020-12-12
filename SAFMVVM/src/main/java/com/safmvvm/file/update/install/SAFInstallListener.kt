package com.safmvvm.file.update.install

import android.content.Context
import com.xuexiang.xupdate._XUpdate
import com.xuexiang.xupdate.entity.DownloadEntity
import com.xuexiang.xupdate.entity.UpdateError.ERROR
import com.xuexiang.xupdate.listener.OnInstallListener
import com.xuexiang.xupdate.utils.ApkInstallUtils
import java.io.File
import java.io.IOException

/**
 * 框架自带的安装器，也可以自定义安装器
 */
class SAFInstallListener: OnInstallListener {

    override fun onInstallApk(
        context: Context,
        apkFile: File,
        downloadEntity: DownloadEntity
    ): Boolean {
        try {
            //TODO AppInit设置是否支持静默安装
            return ApkInstallUtils.install(context, apkFile)
        }catch (ioe: IOException){
            ioe.printStackTrace()
            _XUpdate.onUpdateError(ERROR.INSTALL_FAILED, "获取apk的路径出错！")
        } catch (e: Exception) {
            e.printStackTrace()
            _XUpdate.onUpdateError(ERROR.INSTALL_FAILED, "安装失败！")
        }
        return false
    }

    override fun onInstallApkSuccess() {

    }




}