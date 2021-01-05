package com.deti.basis.login.verification

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentVerificationBinding
import com.safmvvm.mvvm.view.BaseFragment

class VerificationFragment: BaseFragment<BasisFragmentVerificationBinding, VerificationViewModel>(
    R.layout.basis_fragment_verification,
    BR.viewModel
)