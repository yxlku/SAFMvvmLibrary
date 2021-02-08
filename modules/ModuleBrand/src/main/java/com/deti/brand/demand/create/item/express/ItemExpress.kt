package com.deti.brand.demand.create.item.express

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandExpressBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.safmvvm.ext.rvIsShow
import com.test.common.ui.popup.custom.tip.createDialogTitleTip

/**
 * 快递信息
 */
class ItemExpress(
    var activity: Activity?,
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemExpressEntity, BrandItemDemandExpressBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandExpressBinding>,
        data: ItemExpressEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            viewModel = mViewModel
            tvAdressDeti.setOnClickListener {
                activity?.apply {
                    "浙江省杭州市余杭区临平区余杭商会大厦C座 联系电话：123 4567 8910".createDialogTitleTip(this, it).show()
                }
            }
            holder.itemView.rvIsShow(data.isShow)
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandExpressBinding = BrandItemDemandExpressBinding.inflate(layoutInflater, parent, false)
}