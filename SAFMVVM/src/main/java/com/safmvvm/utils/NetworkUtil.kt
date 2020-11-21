package com.safmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.safmvvm.app.BaseApp

/**
 * 网络工具类
 */
object NetworkUtil {
    fun isConnected(): Boolean {
        val manager = BaseApp.getInstance().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as? ConnectivityManager?
        if (manager != null) {
            val info = manager.activeNetworkInfo
            return info != null && info.isAvailable
        }
        return false
    }
}
