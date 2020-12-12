package com.longpc.testapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.collection.ArrayMap
import androidx.lifecycle.Observer
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.StringUtils
import com.loper7.date_time_picker.dialog.CardDatePickerDialog
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.maning.updatelibrary.InstallUtils
import com.maning.updatelibrary.InstallUtils.DownloadCallBack
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.file.update.ApkDownInstaller
import com.safmvvm.file.update.dialog.DefaultUpdateVersionProgressDialog
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.Utils


class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
) {
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

        LiveDataBus.observe(this, "updateVersion", Observer {
            var path = "https://c64126c621520bcd43dc748afae8aa94.dlied1.cdntips.net/imtt.dd.qq.com/16891/apk/653E3126C75A4C8D0EC4292504F988CE.apk"
            ApkDownInstaller.apkDownload(this, path, false)
        })

        LiveDataBus.observe(this, "timeDialog", Observer {
            val displayList = mutableListOf<Int>()
            displayList.add(DateTimeConfig.YEAR)
            displayList.add(DateTimeConfig.MONTH)
            displayList.add(DateTimeConfig.DAY)
            var model = CardDatePickerDialog.STACK
            CardDatePickerDialog.builder(this)
                .setTitle("这个时间选择器牛逼，帅！")
                .setDisplayType(displayList)
//                .setBackGroundModel(model)
                .showBackNow(false)
                .setWrapSelectorWheel(true)
                .setThemeColor(0)
//                .showDateLabel(true)
                .showDateLabel(false)
                .showFocusDateInfo(false)
                .setOnChoose("选择") {
                    mBinding.mainBtnTime.text = "◉  ${
                        StringUtils.conversionTime(
                            it,
                            "yyyy-MM-dd HH:mm:ss"
                        )
                    }    ${StringUtils.getWeek(it)}"
                }
                .setOnCancel("关闭") {
                }.build().show()
        })

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