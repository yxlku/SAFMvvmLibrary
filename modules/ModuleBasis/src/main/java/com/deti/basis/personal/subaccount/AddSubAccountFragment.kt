package com.deti.basis.personal.subaccount

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentAddSubAccountBinding
import com.safmvvm.mvvm.view.BaseFragment

class AddSubAccountFragment: BaseFragment<BasisFragmentAddSubAccountBinding, AddSubAccountViewModel>(
    R.layout.basis_fragment_add_sub_account,
    BR.viewModel
)