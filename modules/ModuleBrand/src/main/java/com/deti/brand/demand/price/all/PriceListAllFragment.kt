package com.deti.brand.demand.price.all

import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentPriceListAllBinding
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 报价全部列表
 */
class PriceListAllFragment: BaseLazyFragment<BrandFragmentPriceListAllBinding, PriceListAllViewModel>(
    R.layout.brand_fragment_price_list_all,
    BR.viewModel
) {


    companion object{
        /** 全部*/
        const val PRICE_LIST_ALL = "Price_list_all"
        /** 未确认*/
        const val PRICE_LIST_UNCONFIRMED = "Price_list_unconfirmed"
        /** 已确认*/
        const val PRICE_LIST_CONFIRMED = "Price_list_confirmed"
        /** 已关闭*/
        const val PRICE_LIST_CLOSED = "Price_list_closed"
    }

    var mAdapter = PriceListAllAdapter(activity)

    override fun initData() {
        super.initData()


    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(testData())
        //第一显示的时候才会请求数据
        mViewModel.requestFindDemandIndentListAPP()
    }

    fun testData(): ArrayList<PriceListAllEntity>{
        var list = arrayListOf<PriceListAllEntity>()
        for (i in 0 until 10){
            list.add(
                PriceListAllEntity(
                    "i",
                    "i"
                )
            )
        }
        return list
    }
}