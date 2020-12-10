package com.longpc.testapplication

import android.app.Activity
import android.os.Bundle
import androidx.collection.ArrayMap
import androidx.lifecycle.Observer
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.Utils


class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
){

    override fun initData() {
        //https://github.com/getActivity/XXPermissions
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            // 申请多个权限
//            .permission(Permission.Group.CALENDAR)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    if (all) {
                        ToastUtil.showShortToast("获取拍照权限成功")
                    }
                }
                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {
                        ToastUtil.showShortToast("被永久拒绝授权，请手动授予拍照权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@MainActivity, permissions)
                    } else {
                        ToastUtil.showShortToast("获取拍照权限失败")
                    }
                }
            })
    }
    override fun initViewObservable() {
        //设置自定义弹窗
//        setCustomDialog(R.layout.main_dialog_cus_test, "")
        setCustomDialog()

        // vm 可以启动界面
        LiveDataBus.observe<Class<out Activity>>(
            this,
            "???",
            Observer {
                startActivity(it)
            },
            true
        )


    }
    fun startActivity(
        clz: Class<out Activity>?,
        map: ArrayMap<String, *>? = null,
        bundle: Bundle? = null
    ) {
        startActivity(Utils.getIntentByMapOrBundle(this, clz, map, bundle))
    }

    override fun onLoadSirReload() {
        mViewModel.testPostFlow()
    }
}