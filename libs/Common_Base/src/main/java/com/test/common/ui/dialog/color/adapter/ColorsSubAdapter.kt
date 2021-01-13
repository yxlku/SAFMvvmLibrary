package com.test.common.ui.dialog.color.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsSubBinding
import com.test.common.ui.dialog.color.ColorsSubEntity

class ColorsSubAdapter: BaseQuickAdapter<ColorsSubEntity, BaseDataBindingHolder<BaseDialogItemColorsSubBinding>>(
    R.layout.base_dialog_item_colors_sub
) {
    var mViewModel = ColorsSubViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsSubBinding>,
        item: ColorsSubEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            if (item.isCheck == true) {
                llLayout.setBackgroundColor(Color.parseColor("#66E5E6E8"))
            } else {
                llLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            executePendingBindings()
        }

    }
}