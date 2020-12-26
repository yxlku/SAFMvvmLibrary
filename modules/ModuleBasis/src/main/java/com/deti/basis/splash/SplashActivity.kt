package com.deti.basis.splash

import com.deti.basis.R
import com.deti.basis.databinding.BasisActivitySplashBinding
import com.safmvvm.mvvm.view.BaseActivity

import com.deti.basis.BR

class SplashActivity: BaseActivity<BasisActivitySplashBinding, SplashViewModel>(
    R.layout.basis_activity_splash,
    BR.viewModel
) {
    override fun startPageAnim() {
//        super.startPageAnim()
    }
}