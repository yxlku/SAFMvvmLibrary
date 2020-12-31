package com.deti.basis.login

import android.app.ActivityManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.billy.android.swipe.SmartSwipe
import com.billy.android.swipe.consumer.ActivitySlidingBackConsumer
import com.deti.basis.R
import com.deti.basis.databinding.BasisActivityLoginBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.deti.basis.BR
import com.safmvvm.app.AppActivityManager
import com.test.common.RouterActivityPath


@Route(path = RouterActivityPath.ModuleBasis.PAGE_LOGIN)
class LoginActivity: BaseActivity<BasisActivityLoginBinding, LoginViewModel>(
    R.layout.basis_activity_login,
    BR.viewModel
) {

    override fun initData() {
        cleanSwipeback()
    }

    override fun onBackPressed() {
        //B端App必须登录，如果不登录，则直接退出，如果弹出此页面也就意味着不登录进不了其他页面，返回理应退出app
        AppActivityManager.finishAllActivity()
    }

    override fun finishPageAnim() {

    }


}