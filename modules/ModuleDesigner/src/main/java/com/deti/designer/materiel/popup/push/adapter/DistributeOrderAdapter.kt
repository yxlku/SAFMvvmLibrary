package com.deti.designer.materiel.popup.push.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemDistributeOrderBinding
import com.deti.designer.materiel.popup.push.entity.DistributeOrderEntity
import com.deti.designer.materiel.popup.push.order.DistributeOrderFragment

/**
 * 抢单信息item
 */
class DistributeOrderAdapter(
    var type: String
): BaseQuickAdapter<DistributeOrderEntity, BaseDataBindingHolder<DesignerItemDistributeOrderBinding>>(
    R.layout.designer_item_distribute_order
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemDistributeOrderBinding>,
        item: DistributeOrderEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            if (item.isChecked) {
                //选中
                ivCheck.setImageResource(R.drawable.base_dialog_checkbox_selected)
            }else{
                ivCheck.setImageResource(R.drawable.base_dialog_checkbox_unselect)
            }
            //代采商字段
            llThree.visibility = if (type == DistributeOrderFragment.ORDER_DISPATCH) {
                View.VISIBLE
            }else{
                View.GONE
            }
            //选中
            ivCheck.setOnClickListener{
                item.isChecked = !item.isChecked
                notifyDataSetChanged()
            }
            executePendingBindings()
        }
    }
}