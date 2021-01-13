package com.test.common.ui.dialog.color.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsSelectedBinding
import com.test.common.ui.dialog.color.ColorsSubEntity

class ColorsSelectedAdapter: BaseQuickAdapter<ColorsSubEntity, BaseDataBindingHolder<BaseDialogItemColorsSelectedBinding>>(
    R.layout.base_dialog_item_colors_selected
) {
    var mViewModel = ColorSelectedViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsSelectedBinding>,
        item: ColorsSubEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            executePendingBindings()
        }
    }
}