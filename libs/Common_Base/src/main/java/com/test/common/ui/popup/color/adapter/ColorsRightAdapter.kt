package com.test.common.ui.popup.color.adapter

import android.graphics.Color
import android.view.View
import androidx.collection.ArraySet
import androidx.collection.arraySetOf
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsRightBinding
import com.test.common.ui.popup.color.DemandColorDataBean

class ColorsRightAdapter(): BaseQuickAdapter<DemandColorDataBean, BaseDataBindingHolder<BaseDialogItemColorsRightBinding>>(
    R.layout.base_dialog_item_colors_right
) {
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsRightBinding>,
        item: DemandColorDataBean,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item

            if (item.mIsCheck) {
                llLayout.setBackgroundColor(Color.parseColor("#66E5E6E8"))
                ivArrow.visibility = View.VISIBLE
            } else {
                llLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
                ivArrow.visibility = View.INVISIBLE
            }
            executePendingBindings()
        }

    }
}