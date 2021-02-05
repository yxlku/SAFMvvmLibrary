package com.deti.brand.demand.detail.other

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentCostOtherBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 其他费用
 */
class OtherCostFragment: BaseLazyFragment<BrandFragmentCostOtherBinding, OtherCostViewModel>(
    R.layout.brand_fragment_cost_other,
    BR.viewModel
) {
}