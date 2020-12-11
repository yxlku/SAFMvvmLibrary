package com.longpc.testapplication

import android.app.Activity
import android.os.Bundle
import androidx.collection.ArrayMap
import androidx.core.graphics.PathUtils
import androidx.lifecycle.Observer
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.StringUtils
import com.loper7.date_time_picker.dialog.CardDatePickerDialog
import com.lxj.xpopup.XPopup
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.file.update.dialog.DefaultUpdateVersionProgressDialog
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.Utils
import com.xuexiang.xupdate.XUpdate
import com.xuexiang.xupdate._XUpdate
import com.xuexiang.xupdate.service.OnFileDownloadListener
import com.xuexiang.xupdate.utils.FileUtils.getExtDownloadsPath


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

        LiveDataBus.observe(this, "updateVersion", Observer {
            XPopup.Builder(this)
                .hasBlurBg(true) //高斯模糊
                .dismissOnBackPressed(true) // 按返回键是否关闭弹窗，默认为true
                .dismissOnTouchOutside(true) // 点击外部是否关闭弹窗，默认为true
                .asCustom(DefaultUpdateVersionProgressDialog(this))
                .show()
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

//    fun downLoad(){
//        _XUpdate.startInstallApk(this, FileUtils.getFileByPath(PathUtils.getFilePathByUri(this, data.getData()))); //填写文件所在的路径
//
//        XUpdate.newBuild(this)
////            .apkCacheDir(PathUtils.getExtDownloadsPath()) //设置下载缓存的根目录
//            .build()
//            .download(mDownloadUrl, object : OnFileDownloadListener{
//                //设置下载的地址和下载的监听
//                override fun onStart() {
//                    HProgressDialogUtils.showHorizontalProgressDialog(getContext(), "下载进度", false)
//                }
//
//                override fun onProgress(progress: Float, total: Long) {
//                    HProgressDialogUtils.setProgress(Math.round(progress * 100))
//                }
//
//                override fun onCompleted(file: File): Boolean {
//                    HProgressDialogUtils.cancel()
//                    ToastUtils.toast("apk下载完毕，文件路径：" + file.getPath())
//                    return false
//                }
//
//                override fun onError(throwable: Throwable?) {
//                    HProgressDialogUtils.cancel()
//                }
//            })
//    }

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