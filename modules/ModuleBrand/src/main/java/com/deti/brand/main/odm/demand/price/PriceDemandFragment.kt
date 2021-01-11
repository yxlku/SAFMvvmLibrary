package com.deti.brand.main.odm.demand.price

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentDemandPriceBinding
import com.safmvvm.mvvm.view.BaseFragment

class PriceDemandFragment: BaseFragment<BrandFragmentDemandPriceBinding, PriceDemandViewModel>(
    R.layout.brand_fragment_demand_price,
    BR.viewModel
)