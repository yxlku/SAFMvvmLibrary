package com.deti.brand.demand.price.pricelist.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListBinding

class PriceListAdapter: BaseQuickAdapter<PriceListEntity, BaseDataBindingHolder<BrandItemPriceListBinding>>(
    R.layout.brand_item_price_list
) {
    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPriceListBinding>,
        item: PriceListEntity,
    ) {
        var mViewModel = PriceListItemViewModel()
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            executePendingBindings()
        }

    }
}