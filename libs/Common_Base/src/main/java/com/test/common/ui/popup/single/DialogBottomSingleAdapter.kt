package com.test.common.ui.popup.single

import android.view.View
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.databinding.BaseItemDialogCommonSingleBinding
import com.test.common.ui.popup.base.BaseSingleChoiceEntity

class DialogBottomSingleAdapter(
    @LayoutRes itemLayoutID : Int,
    var selectedPosition: Int = 0
) : BaseQuickAdapter<BaseSingleChoiceEntity, BaseDataBindingHolder<BaseItemDialogCommonSingleBinding>>(
        itemLayoutID
    ) {

    override fun convert(
        holder: BaseDataBindingHolder<BaseItemDialogCommonSingleBinding>,
        item: BaseSingleChoiceEntity,
    ) {
        holder.dataBinding?.apply {
            entity = item
            ivChooseState.visibility =
                if (holder.adapterPosition == selectedPosition) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            executePendingBindings()
        }
    }


}