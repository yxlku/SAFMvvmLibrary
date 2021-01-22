package com.test.common.ui.popup.color.adapter

import android.graphics.Color
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsLeftBinding
import com.test.common.ui.popup.color.DemandColorListDataBean

class ColorsLeftAdapter(
    var selectedPosition : Int = -1
): BaseQuickAdapter<DemandColorListDataBean, BaseDataBindingHolder<BaseDialogItemColorsLeftBinding>>(
    R.layout.base_dialog_item_colors_left
) {
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsLeftBinding>,
        item: DemandColorListDataBean,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            if(holder.adapterPosition == selectedPosition) {
                llLayout.setBackgroundColor(Color.parseColor("#66E5E6E8"))
                ivImg.visibility = View.VISIBLE
            }else{
                llLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
                ivImg.visibility = View.INVISIBLE
            }
            executePendingBindings()
        }
    }
}