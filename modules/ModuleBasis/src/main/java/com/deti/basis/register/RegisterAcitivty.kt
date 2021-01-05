package com.deti.basis.register

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisActivityRegisterBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.theme.StatusBarUtil

class RegisterAcitivty: BaseActivity<BasisActivityRegisterBinding, RegisterViewModel>(
    R.layout.basis_activity_register,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        StatusBarUtil.immersionPageView(this, mBinding.basisClose)
        StatusBarUtil.immersionPageView(this, mBinding.basisLogo)
    }
}