package com.deti.brand

import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityBrandBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBrand.PAGE_MAIN)
class BrandMainActivity: BaseActivity<BrandActivityBrandBinding, BrandViewModel>(
    R.layout.brand_activity_brand,
    BR.viewModel
) {


}