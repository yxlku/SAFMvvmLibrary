package com.longpc.testapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.collection.ArrayMap
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.jaredrummler.cyanea.prefs.CyaneaTheme
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
import com.safmvvm.ui.theme.ThemeUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.Utils
import com.test.common.RouterActivityPath
import java.lang.Exception


@Route(path = RouterActivityPath.TestApplication.PAGE_MAIN)
class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
) {

    //ARouter，除了使用注解@Autowired（必须在activity或fragment中获取），
    // 也可以使用普通传参方式，在ViewModel中接收借口
    //必须使用@JvmField
    @JvmField
    @Autowired(name = "routerUtils")
    var routerUtils = ""
    @JvmField
    @Autowired(name = "test")
    var test = ""

    override fun initData() {
        mViewModel.text.set(test+"ssss2")
//        LogUtil.d(routerUtils)
        //https://github.com/getActivity/XXPermissions
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            // 申请多个权限
//            .permission(Permission.Group.CALENDAR)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    //这里每次调用时都会提示
                    if (all) {
//                        ToastUtil.showShortToast("获取拍照权限成功")
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

        LiveDataBus.observe<CyaneaTheme>(this, "theme", Observer {
            //主题切换
            ThemeUtil.applyTheme(this, cyanea, it)
        }, false)

        LiveDataBus.observe(this, "updateVersion", Observer {
            var isForce: Boolean = false
            var path = "https://fga1.market.xiaomi.com/download/AppStore/03fcb41660d98ef8d4f70586913fc0e8ccf41accb/mobi.detiplatform.apk"
//            var path = ""
            ApkDownInstaller.apkDownload(this, path, isForce, installCallBack = object :InstallUtils.InstallCallBack{
                override fun onSuccess() {
                    ApkDownInstaller.tipAndToBrower(this@MainActivity, "不安装，去别的地方下载吧", isForce, path)
                }

                override fun onFail(e: Exception?) {
                    ApkDownInstaller.tipAndToBrower(this@MainActivity, "安装失败了，去别的地方下载吧", isForce, path)
                }

            })
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

    override fun onLoadSirReload() {
        //重新加载
        mViewModel.testPostFlow()
    }
}