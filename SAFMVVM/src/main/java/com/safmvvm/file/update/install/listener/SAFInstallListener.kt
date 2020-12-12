package com.safmvvm.file.update.install.listener

import android.content.Context
import com.safmvvm.file.update.install.ApkDownloadEntity
import java.io.File

/**
 * 框架自带的安装器，也可以自定义安装器
 */
class SAFInstallListener: OnInstallListener {
    override fun onInstallApk(
        context: Context,
        apkFile: File,
        apkDownloadEntity: ApkDownloadEntity
    ): Boolean {

        return ApkInstallUtils.install(context,apkFile)

    }

    override fun onInstallApkSuccess() {
    }

    override fun onInstallApkError() {
    }



}