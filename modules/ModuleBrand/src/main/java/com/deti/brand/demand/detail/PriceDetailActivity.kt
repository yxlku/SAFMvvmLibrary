package com.deti.brand.demand.detail

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandActivityPriceDetailBinding
import com.deti.brand.demand.detail.material.MaterialCostFragment
import com.deti.brand.demand.detail.other.OtherCostFragment
import com.deti.brand.demand.detail.other.OtherCostViewModel
import com.deti.brand.demand.detail.total.TotalCostFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.theme.StatusBarUtil

/**
 * 报价详情
 */
class PriceDetailActivity: BaseActivity<BrandActivityPriceDetailBinding, PriceDetailViewModel>(
    R.layout.brand_activity_price_detail,
    BR.viewModel
) , ITabTop{

    companion object{
        fun startAction(activity: Activity?){
            activity?.apply {
                var intent = Intent(activity, PriceDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    var titles = arrayListOf("物料成本", "其他费用", "合计报价")
    var fragments = arrayListOf<Fragment>()

    override fun initData() {
        super.initData()

        StatusBarUtil.init(this, false)

        fragments.apply {
            add(MaterialCostFragment())
            add(OtherCostFragment())
            add(TotalCostFragment())
        }.createViewPager(supportFragmentManager, mBinding.vpContent)

        initTabTop(this, mBinding.miTab, mBinding.vpContent, titles, true)
    }
}