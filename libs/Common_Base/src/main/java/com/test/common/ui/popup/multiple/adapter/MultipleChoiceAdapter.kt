package com.test.common.ui.popup.multiple.adapter

import android.widget.CompoundButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.test.common.R
import com.test.common.databinding.BaseItemDialogCommonMultipleBinding
import com.test.common.ui.popup.multiple.BaseMultipleChoiceEntity

class MultipleChoiceAdapter(
    var callback: ((buttonView: CompoundButton?, isChecked: Boolean, entity: BaseMultipleChoiceEntity) -> Unit?)? = null,
): BaseQuickAdapter<BaseMultipleChoiceEntity, BaseDataBindingHolder<BaseItemDialogCommonMultipleBinding>>(
    R.layout.base_item_dialog_common_multiple
) {
    override fun convert(
        holder: BaseDataBindingHolder<BaseItemDialogCommonMultipleBinding>,
        item: BaseMultipleChoiceEntity,
    ) {

        holder.dataBinding?.apply {
            entity = item
            cbItem.setOnCheckedChangeListener { buttonView, isChecked ->
                item.isSelected = isChecked
                callback?.let { it(buttonView, isChecked, item) }
            }
            executePendingBindings()
        }
    }



}