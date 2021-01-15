package com.deti.brand.demand.price.all.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListBtnBinding
import com.deti.brand.demand.price.all.entity.PriceListAllBtnEntity

class PriceListAllBtnAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<PriceListAllBtnEntity, BaseDataBindingHolder<BrandItemPriceListBtnBinding>>(
    R.layout.brand_item_price_list_btn
) {
    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPriceListBtnBinding>,
        item: PriceListAllBtnEntity,
    ) {
        var mViewMode = PriceListAllBtnViewModel(mActivity, this)
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewMode
            executePendingBindings()
        }
    }

}