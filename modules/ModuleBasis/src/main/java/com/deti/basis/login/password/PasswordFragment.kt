package com.deti.basis.login.password

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentPasswordBinding
import com.safmvvm.mvvm.view.BaseFragment

class PasswordFragment : BaseFragment<BasisFragmentPasswordBinding, PasswordViewModel>(
    R.layout.basis_fragment_password,
    BR.viewModel
)