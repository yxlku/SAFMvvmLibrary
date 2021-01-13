package com.test.common.ui.dialog.color.adapter

import android.graphics.Color
import android.view.View
import android.widget.CompoundButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.bus.putValue
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsBinding
import com.test.common.ui.dialog.color.ColorsEntity

class ColorsAdapter: BaseQuickAdapter<ColorsEntity, BaseDataBindingHolder<BaseDialogItemColorsBinding>>(
    R.layout.base_dialog_item_colors
) {
    var mViewModel = ColorsViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsBinding>,
        item: ColorsEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            if(holder.adapterPosition == mViewModel.selectedPosition.value) {
                cbItem.setBackgroundColor(Color.parseColor("#66E5E6E8"))
            }else{
                cbItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            executePendingBindings()
        }
    }
}