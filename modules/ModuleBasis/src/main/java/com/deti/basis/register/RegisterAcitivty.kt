package com.deti.basis.register

import android.view.View
import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisActivityRegisterBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.theme.StatusBarUtil
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar

class RegisterAcitivty: BaseActivity<BasisActivityRegisterBinding, RegisterViewModel>(
    R.layout.basis_activity_register,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        StatusBarUtil.statusTextAndIconColor(this, true)
    }
}