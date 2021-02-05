package com.deti.brand.demand.detail.total

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentCostTotalBinding
import com.deti.brand.demand.detail.entity.OtherCostResultEntity
import com.deti.brand.demand.detail.entity.TotalCostResultEntity
import com.deti.brand.demand.detail.other.OtherCostFragment
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.test.common.ui.item.infodetail.ItemChoose
import com.test.common.ui.item.infodetail.ItemChooseEntity
import com.test.common.ui.item.infotitle.ItemInfoTitle
import com.test.common.ui.item.infotitle.ItemInfoTitleEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

/**
 * 合计报价
 */
class TotalCostFragment: BaseLazyFragment<BrandFragmentCostTotalBinding, TotalCostViewModel>(
    R.layout.brand_fragment_cost_total,
    BR.viewModel
) {

    companion object{
        /** 刷新数据*/
        const val LIVE_DATA_TOTAL_COST = "live_data_total_cost"
    }

    var mAdapter = BaseBinderAdapter()

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        //请求
        mViewModel.requestLastQuoteInfo()

        mAdapter.apply {
            addItemBinder(ItemChooseEntity::class.java, ItemChoose())
            addItemBinder(ItemInfoTitleEntity::class.java, ItemInfoTitle())
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
        }
        mBinding?.apply {
            rvContent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
        }

        LiveDataBus.observe<TotalCostResultEntity>(this, LIVE_DATA_TOTAL_COST, {
            var listData = arrayListOf(
                ItemChooseEntity("", "物料合计", it.materialPrice, "元"),
                ItemChooseEntity("", "特殊工艺合计", "没找着。。。", "元"),
                ItemChooseEntity("", "其他费用", it.otherPrice, "元"),
                ItemChooseEntity("", "测试标准", it.standard),
                ItemChooseEntity("", "安全技术要求", it.safetyCategory),
                ItemChooseEntity("", "利润", it.profit, "%"),
                ItemChooseEntity("", "增值税", it.valueAddTax, "%"),
                ItemChooseEntity("", "总成本合计", it.costPrice, "元"),
                ItemChooseEntity("", "报价", "没找着。。", "元"),
                ItemTransparentLineEntity(context, 10F),
                ItemInfoTitleEntity("", it.quoteTime, false),
                ItemChooseEntity("", "0-100件", it.firstTieredPrice, "元"),
                ItemChooseEntity("", "101-500件", it.secondTieredPrice, "元"),
                ItemChooseEntity("", "501-2000件", it.thirdTieredPrice, "元"),
                ItemChooseEntity("", "2001-5000件", it.fourthTieredPrice, "元"),
                ItemChooseEntity("", "5001-10000件", it.fifthTieredPrice, "元"),
                ItemChooseEntity("", "10000件以上", it.sixthTieredPrice, "元"),
            )
            mAdapter.setList(listData)
        }, false)
    }
}