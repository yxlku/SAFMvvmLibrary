package com.deti.brand.demand.price.all.adapter

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.price.all.entity.PriceListAllBtnEntity
import com.deti.brand.demand.price.all.entity.PriceListAllEntity

class PriceListAllAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
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

            var btnAdapter = PriceListAllBtnAdapter(mActivity)
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnAdapter
            }
            var listBtn: ArrayList<PriceListAllBtnEntity> = arrayListOf<PriceListAllBtnEntity>()
            when (item.state) {
                0 -> {
                    //查看物流、我要修改
                    listBtn.add(
                        PriceListAllBtnEntity(
                            0,
                            "查看物流",
                            0,
                            R.drawable.base_btn_yellow_bg
                        )
                    )
                    listBtn.add(
                        PriceListAllBtnEntity(
                            0,
                            "我要修改",
                            0,
                            R.drawable.base_btn_black_bg
                        )
                    )
                    listBtn.add(
                        PriceListAllBtnEntity(
                            0,
                            "我要修改2",
                            0,
                            R.drawable.base_btn_black_bg
                        )
                    )
                }
                else -> {
                    //查看物流、我要修改
                    listBtn.add(
                        PriceListAllBtnEntity(
                            0,
                            "查看物流else",
                            0,
                            R.drawable.base_btn_yellow_bg
                        )
                    )
                    listBtn.add(
                        PriceListAllBtnEntity(
                            0,
                            "我要修改else",
                            0,
                            R.drawable.base_btn_black_bg
                        )
                    )
                }
            }
            btnAdapter.setList(listBtn)
        }
    }
}