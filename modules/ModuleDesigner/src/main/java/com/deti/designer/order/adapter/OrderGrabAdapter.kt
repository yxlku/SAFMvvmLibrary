package com.deti.designer.order.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemOrderGrabBinding
import com.deti.designer.order.OrderGrabEntity
import com.deti.designer.order.OrderGrabFragment

class OrderGrabAdapter(
    var mState: Int = OrderGrabFragment.STATE_GRAB
): BaseQuickAdapter<OrderGrabEntity, BaseDataBindingHolder<DesignerItemOrderGrabBinding>>(
    R.layout.designer_item_order_grab
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemOrderGrabBinding>,
        item: OrderGrabEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            //控制按钮
            controlBtns(binding)
            executePendingBindings()
        }

    }

    private fun controlBtns(binding: DesignerItemOrderGrabBinding) {
        binding.apply {
            if (mState == OrderGrabFragment.STATE_GRAB) {
                //抢单
                tvBtnYellow.text = "立即抢单"
                tvBtnWhite.visibility = View.VISIBLE
            } else {
                //派单
                tvBtnYellow.text = "立即接单"
                tvBtnWhite.visibility = View.GONE
            }
        }
    }
}