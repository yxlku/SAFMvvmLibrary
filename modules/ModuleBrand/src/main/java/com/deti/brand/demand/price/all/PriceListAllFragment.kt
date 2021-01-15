package com.deti.brand.demand.price.all

import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentPriceListAllBinding
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.safmvvm.mvvm.view.BaseFragment

/**
 * 报价全部列表
 */
class PriceListAllFragment: BaseFragment<BrandFragmentPriceListAllBinding, PriceListAllViewModel>(
    R.layout.brand_fragment_price_list_all,
    BR.viewModel
) {

    var mAdapter = PriceListAllAdapter(activity)
    override fun initData() {
        super.initData()

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(testData())
    }

    fun testData(): ArrayList<PriceListAllEntity>{
        var list = arrayListOf<PriceListAllEntity>()
        for (i in 0 until 10){
            list.add(
                PriceListAllEntity(
                    i,
                    i
                )
            )
        }
        return list
    }
}