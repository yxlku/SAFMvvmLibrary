package com.deti.brand.demand.price.all

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentPriceListAllBinding
import com.deti.brand.demand.detail.PriceDetailActivity
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.safmvvm.bus.LiveDataBus
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
        const val PRICE_LIST_ALL = "1"
        /** 未确认*/
        const val PRICE_LIST_UNCONFIRMED = "2"
        /** 已确认*/
        const val PRICE_LIST_CONFIRMED = "3"
        /** 已关闭*/
        const val PRICE_LIST_CLOSED = "4"

        /** 添加数据*/
        const val DATA_ADD = "data_add"
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        var mAdapter = PriceListAllAdapter(activity)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                //报价详情
                PriceDetailActivity.startAction(activity)
            }
        })

        //第一显示的时候才会请求数据
        mViewModel.requestFindDemandIndentListAPP(PRICE_LIST_ALL)

        //刷新数据
        LiveDataBus.observe<ArrayList<PriceListAllEntity>>(this, DATA_ADD, {
              mAdapter.setList(it)
        }, false)
    }

}