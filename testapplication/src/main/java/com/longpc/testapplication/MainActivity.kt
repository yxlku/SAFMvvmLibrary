package com.longpc.testapplication

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.collection.ArrayMap
import androidx.lifecycle.Observer
import com.ftd.livepermissions.LivePermissions
import com.ftd.livepermissions.PermissionResult
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.utils.Utils

class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
){

    override fun initData() {
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