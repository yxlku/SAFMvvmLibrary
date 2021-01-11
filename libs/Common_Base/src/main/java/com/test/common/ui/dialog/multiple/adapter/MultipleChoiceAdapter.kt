package com.test.common.ui.dialog.multiple.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseItemDialogCommonMultipleBinding
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity

class MultipleChoiceAdapter: BaseQuickAdapter<BaseMultipleChoiceEntity, BaseDataBindingHolder<BaseItemDialogCommonMultipleBinding>>(
    R.layout.base_item_dialog_common_multiple
) {

    var viewModel = MultipleChoiceViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<BaseItemDialogCommonMultipleBinding>,
        item: BaseMultipleChoiceEntity,
    ) {
        holder.dataBinding?.apply {
            entity = item
            executePendingBindings()
        }
    }

}