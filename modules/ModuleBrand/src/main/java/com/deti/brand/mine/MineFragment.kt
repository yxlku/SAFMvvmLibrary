package com.deti.brand.mine

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentMineBinding
import com.safmvvm.mvvm.view.BaseFragment

class MineFragment: BaseFragment<BrandFragmentMineBinding, MineViewModel>(
    R.layout.brand_fragment_mine,
    BR.viewModel
)