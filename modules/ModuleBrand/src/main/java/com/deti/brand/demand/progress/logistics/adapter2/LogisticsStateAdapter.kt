package com.deti.brand.demand.progress.logistics.adapter2

import android.app.Activity
import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemLogisticsStateBinding
import com.deti.brand.demand.progress.OrderStatus
import com.safmvvm.utils.ResUtil
import com.test.common.ui.view.TimelineView

class LogisticsStateAdapter(
   var mActivity: Activity?
): BaseQuickAdapter<LogisticsStateEntity, BaseDataBindingHolder<BrandItemLogisticsStateBinding>>(
    R.layout.brand_item_logistics_state,
) {
    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }
    override fun convert(
        holder: BaseDataBindingHolder<BrandItemLogisticsStateBinding>,
        item: LogisticsStateEntity,
    ) {
        var mViewModel = LogisticsStateViewModel()
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            binding.tivStatus.initLine(holder.itemViewType)
            initStatusUI(holder, binding, item)
            executePendingBindings()
        }
    }

    private fun initStatusUI(
        holder: BaseDataBindingHolder<BrandItemLogisticsStateBinding>,
        binding: BrandItemLogisticsStateBinding,
        item: LogisticsStateEntity,
    ) {
        when (item.status) {
            OrderStatus.INACTIVE -> {
                binding.tivStatus.marker = ResUtil.getDrawable(item.iconUnCheck)
                binding.tvStatusText.setTextColor(Color.parseColor("#E9E9E9"))
            }
            OrderStatus.ACTIVE -> {
                binding.tivStatus.marker = ResUtil.getDrawable(item.iconCheck)
                binding.tvStatusText.setTextColor(Color.parseColor("#FCCE48"))
            }
            else ->{
                binding.tivStatus.marker = ResUtil.getDrawable(0)
            }
        }
    }

}