package com.deti.brand.main.oem

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentIndexOemBinding
import com.safmvvm.mvvm.view.BaseFragment

class OEMFragment: BaseFragment<BrandFragmentIndexOemBinding, OEMViewModel>(
    R.layout.brand_fragment_index_oem,
    BR.viewModel
)