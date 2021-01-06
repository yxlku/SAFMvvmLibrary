package com.deti.brand.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentMainBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

class MainFragment: BaseActivity<BrandFragmentMainBinding, MainViewModel>(
    R.layout.brand_fragment_main,
    BR.viewModel
)