package com.test.common.ui.popup.custom.color.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemColorsSelectedBinding
import com.test.common.ui.popup.custom.color.DemandColorDataBean

/**
 * 顶部选中颜色
 */
class ColorsSelectedAdapter: BaseQuickAdapter<DemandColorDataBean, BaseDataBindingHolder<BaseDialogItemColorsSelectedBinding>>(
    R.layout.base_dialog_item_colors_selected
) {
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemColorsSelectedBinding>,
        item: DemandColorDataBean,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()
        }
    }
}