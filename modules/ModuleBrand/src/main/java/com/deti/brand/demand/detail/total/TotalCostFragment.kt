package com.deti.brand.demand.detail.total

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentCostTotalBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 合计报价
 */
class TotalCostFragment: BaseLazyFragment<BrandFragmentCostTotalBinding, TotalCostViewModel>(
    R.layout.brand_fragment_cost_total,
    BR.viewModel
) {
}