package com.deti.designer.order.popup.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemOrderFilterItemBinding
import com.safmvvm.utils.ResUtil
import com.test.common.ui.popup.base.BaseDialogSingleEntity

/**
 * 筛选弹窗按钮适配器
 */
class FilterOrderPopupAdapter(
    var selectedPosition: Int = -1,
): BaseQuickAdapter<BaseDialogSingleEntity, BaseDataBindingHolder<DesignerItemOrderFilterItemBinding>>(
    R.layout.designer_item_order_filter_item
) {


    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemOrderFilterItemBinding>,
        item: BaseDialogSingleEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            var isChoose = holder.adapterPosition == selectedPosition
            if (isChoose) {
                tvBtn.background = ResUtil.getDrawable(R.drawable.base_btn_yellow_bg_round)
                tvBtn.setTextColor(Color.parseColor("#333333"))
            }else{
                tvBtn.background = ResUtil.getDrawable(R.drawable.base_btn_gray_bg_round)
                tvBtn.setTextColor(Color.parseColor("#666666"))
            }
            executePendingBindings()
        }
    }
}