package com.deti.brand.main.odm.demand.create

import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentDemandCreateBinding
import com.safmvvm.mvvm.view.BaseFragment

class CreateDemandFragment: BaseFragment<BrandFragmentDemandCreateBinding, CreateDemandViewModel>(
    R.layout.brand_fragment_demand_create,
    BR.viewModel
)