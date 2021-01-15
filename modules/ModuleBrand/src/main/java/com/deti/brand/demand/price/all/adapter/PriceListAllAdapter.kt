package com.deti.brand.demand.price.all.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.price.all.entity.PriceListAllEntity

class PriceListAllAdapter: BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
    R.layout.brand_item_price_list_all
) {
    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ) {
        var mViewModel = PriceListAllItemViewModel(context, this)
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            executePendingBindings()
        }
    }
}