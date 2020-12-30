package com.example.moduletesta

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.moduletesta.databinding.TestaActivityMainBinding
import com.safmvvm.app.AppActivityManager
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.dialog.DialogUtil
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleTestA.PAGE_TESTA)
class TestAMainActivity : BaseActivity<TestaActivityMainBinding, TestAViewModel>(
    R.layout.testa_activity_main,
    BR.viewModel
) {


    override fun initViewObservable() {
    }

    override fun initData() {
    }

    override fun onBackPressed() {
        //双击模式
//        DoubleClickUtil.checkDoubleClick(
//            1500,
//            "再点一次我就要退出了！！",
//            block = {
//                AppActivityManager.finishAllActivity()
//            },
//            tipBlock = {
//
//            }
//        )
        //弹窗模式
        DialogUtil(this)
            .confirmAndCancelDialog(
                content = "再点一次就要退出了",
                confirmBlock = {
                    AppActivityManager.finishAllActivity()
                }
            ).show()
    }

}