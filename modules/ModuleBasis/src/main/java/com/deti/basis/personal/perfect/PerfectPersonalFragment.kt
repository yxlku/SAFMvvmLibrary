package com.deti.basis.personal.perfect

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentPerfectPersonalBinding
import com.safmvvm.mvvm.view.BaseFragment

class PerfectPersonalFragment: BaseFragment<BasisFragmentPerfectPersonalBinding, PerfectPersonalViewModel>(
    R.layout.basis_fragment_perfect_personal,
    BR.viewModel
)