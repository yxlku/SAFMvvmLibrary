package com.deti.brand.demand.detail.other

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentCostOtherBinding
import com.deti.brand.demand.detail.entity.OtherCostEntity
import com.deti.brand.demand.detail.entity.OtherCostResultEntity
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.test.common.ui.item.infodetail.ItemChoose
import com.test.common.ui.item.infodetail.ItemChooseEntity

/**
 * 其他费用
 */
class OtherCostFragment: BaseLazyFragment<BrandFragmentCostOtherBinding, OtherCostViewModel>(
    R.layout.brand_fragment_cost_other,
    BR.viewModel
) {

    companion object{
        /** 刷新的数据*/
        const val LIVE_DATA_OTHER_COST = "live_data_other_cost"
    }

    var mAdapter = BaseBinderAdapter()

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        //请求
        mViewModel.requestFindFabricList()

        mAdapter.apply {
            addItemBinder(ItemChooseEntity::class.java, ItemChoose())
        }
        mBinding.apply {
            rvContent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
        }

        LiveDataBus.observe<OtherCostResultEntity>(this, LIVE_DATA_OTHER_COST, {
            var listData = arrayListOf(
                ItemChooseEntity("", "加工费", it.processPrice.toString(), "元"),
                ItemChooseEntity("", "运费及保险", it.transportPrice.toString(), "元"),
                ItemChooseEntity("", "小单费", it.smallOrderPrice.toString(), "元"),
                ItemChooseEntity("", "测试费用", it.testPrice.toString(), "元"),
                ItemChooseEntity("", "打版费", it.makeSamplePrice.toString(), "元"),
                ItemChooseEntity("", "其他费用合计", it.otherPrice.toString(), "元"),
            )
            mAdapter.setList(listData)
        }, false)
    }

}