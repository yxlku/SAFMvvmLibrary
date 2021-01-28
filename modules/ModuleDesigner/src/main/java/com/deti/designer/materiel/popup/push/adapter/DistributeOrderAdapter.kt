package com.deti.designer.materiel.popup.push.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemDistributeOrderBinding
import com.deti.designer.materiel.popup.push.entity.DistributeOrderEntity

/**
 * 抢单信息item
 */
class DistributeOrderAdapter: BaseQuickAdapter<DistributeOrderEntity, BaseDataBindingHolder<DesignerItemDistributeOrderBinding>>(
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
            executePendingBindings()
        }
    }
}