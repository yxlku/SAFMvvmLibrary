package com.safmvvm.file.update.install

import java.io.Serializable

/**
 * Apk下载实体
 */
data class ApkDownloadEntity(
    /** 下载地址*/
    var downloadUrl: String,
    /** 下载到手机上的目录*/
    var cacheDir: String,
//    /** 下载文件的加密值，用于校验，防止下载的apk文件被替换【当然你也可以不使用MD5加密】*/
//    var md5: String,
    /** apk文件大小*/
    var apkSize: Long,
    /** 是否显示在通知栏，默认通知栏显示 */
    var isShowNotification: Boolean = true
):Serializable