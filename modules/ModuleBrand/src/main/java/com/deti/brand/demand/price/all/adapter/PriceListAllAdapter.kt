package com.deti.brand.demand.price.all.adapter

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.test.common.ui.adapter.CommonListBtnsAdapter
import com.test.common.ui.adapter.CommonListBtnsEntity

class PriceListAllAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
    R.layout.brand_item_price_list_all
) {


    companion object{

        /*** 报价状态*/
        /** 待得体报价*/
        const val STATE_OFFER_WAIT_DETI = "state_offer_wait_deti"
        /** 待得体报价中*/
        const val STATE_OFFER_ING_DETI = "state_offer_ing_deti"
        /** 待报价*/
        const val STATE_OFFER_WAIT = "state_offer_wait"
        /** 待确认*/
        const val STATE_CONFIRMED_WAIT = "state_confirmed_wait"
        /** 已确认报价*/
        const val STATE_CONFIRMED = "state_confirmed"

        /** 需求取消*/
        const val STATE_DEMAND_CANCEL = "state_demand_cancel"
        /** 已拒绝报价*/
        const val STATE_OFFER_REFUSE = "state_offer_refuse"
    }

    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()

            var btnAdapter = CommonListBtnsAdapter()
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnAdapter
            }
        }
    }

    fun controlBtns(): ArrayList<CommonListBtnsAdapter> {
        var btns = arrayListOf<CommonListBtnsAdapter>()

        return btns
    }
}