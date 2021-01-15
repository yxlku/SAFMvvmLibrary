package com.deti.basis.personal.address

import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisFragmentAddAddressBinding
import com.safmvvm.mvvm.view.BaseFragment

class AddAddressFragment: BaseFragment<BasisFragmentAddAddressBinding, AddAddressViewModel>(
    R.layout.basis_fragment_add_address,
    BR.viewModel
)