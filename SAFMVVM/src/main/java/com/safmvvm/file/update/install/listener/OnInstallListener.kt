package com.safmvvm.file.update.install.listener

import android.content.Context
import com.safmvvm.file.update.install.ApkDownloadEntity
import java.io.File

/**
 * 安装操作监听器
 * 1、安装
 * 2、安装结果
 *  2.1、安装成功处理
 *  2.2、安装失败处理
 */
interface OnInstallListener {

    /**
     * 开始安装，实现类自定义安装策略
     *
     * @param apkFile               安装的apk文件
     * @param apkDownloadEntity     文件下载信息
     */
    fun onInstallApk(context: Context, apkFile: File, apkDownloadEntity: ApkDownloadEntity): Boolean

    fun onInstallApkSuccess()

    fun onInstallApkError()
}