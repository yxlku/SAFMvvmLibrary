package com.safmvvm.file.update.install

import android.Manifest.permission
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import androidx.annotation.RequiresPermission
import com.safmvvm.utils.ShellUtils
import java.io.File

/**
 * Apk安装工具
 */
object ApkInstallUtils {

    /** 是否支持静默安装【默认是true】*/
    var sSupportSilentInstall: Boolean = true

    /**
     * 自适应apk安装（如果设备有root权限就自动静默安装）
     *
     * @param context
     * @param apkFile apk文件
     * @return
     */
    fun install(context: Context, apkFile: File): Boolean{
        if (sSupportSilentInstall) {
            //静默安装
            //系统应用或root直接使用静默安装 ，否则还是普通安装，通过意图安装
            if (isSystemApplication(context) || ShellUtils.checkRootPermission()) {
                return installSilent(context, apkFile)
            }
            return installNormal(context, apkFile)
        }else{
            //系统意图安装（非静默）
            return installNormal(context, apkFile)
        }
    }

    /**
     * 静默安装
     */
    @RequiresPermission(permission.INSTALL_PACKAGES)
    private fun installSilent(context: Context, apkFile: File): Boolean{
        //apk路径
        var apkFileDir: String = apkFile.canonicalPath
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            //Android 7.0以前
            return installAppSilentBelow24(context, apkFile)
        }else{
            //Android 7.0以后
            return installAppSilentAbove24(context, apkFile)
        }
    }

    /**
     * 静默安装（Android 7.0以前）
     */
    private fun installAppSilentBelow24(context: Context, apkFile: File): Boolean{
        return false
    }
    /**
     * 静默安装（Android 7.0以后）
     */
    private fun installAppSilentAbove24(context: Context, apkFile: File): Boolean{
        return false
    }

    /**
     * 普通安装（非静默）
     */
    private fun installNormal(context: Context, apkFile: File): Boolean{
        return true
    }

    /**
     * packageName是否为系统应用程序
     */
    private fun isSystemApplication(context: Context): Boolean{
        context?.let{
            var packageManager = context.packageManager
            var packageName = context.packageName
            if (packageManager == null || packageName == null || packageName.isEmpty()) {
                return false
            }
            try {
                var app: ApplicationInfo = packageManager.getApplicationInfo(packageName, 0)
                return app != null && app.flags and ApplicationInfo.FLAG_SYSTEM > 0
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }
        return false
    }

}