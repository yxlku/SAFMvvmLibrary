package com.deti.brand.demand.price.pricelist

import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentPriceListBinding
import com.deti.brand.demand.price.pricelist.adapter.PriceListAdapter
import com.deti.brand.demand.price.pricelist.adapter.PriceListEntity
import com.safmvvm.mvvm.view.BaseFragment

class PriceListFragment(
    var type: Int = 0
):BaseFragment<BrandFragmentPriceListBinding, PriceListViewModel>(
    R.layout.brand_fragment_price_list,
    BR.viewModel
) {
    var mAdapter = PriceListAdapter()

    override fun initData() {
        super.initData()

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(testData())

    }

    fun testData(): List<PriceListEntity>{
        var listData = arrayListOf<PriceListEntity>()
        for(i in 0 until 5){
            listData.add(PriceListEntity())
        }
        return listData
    }
}