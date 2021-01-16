package com.test.common.ui.dialog.single.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseItemDialogCommonSingleBinding
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity

class SingleChoiceAdapter(
    @LayoutRes itemLayoutID : Int
) : BaseQuickAdapter<BaseSingleChoiceEntity, BaseDataBindingHolder<BaseItemDialogCommonSingleBinding>>(
        itemLayoutID
    ) {
    var mViewModel = SingleChoiceViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<BaseItemDialogCommonSingleBinding>,
        item: BaseSingleChoiceEntity,
    ) {
        holder.dataBinding?.apply {
            entity = item
            viewModel = mViewModel
            ivChooseState.visibility =
                if (holder.adapterPosition == mViewModel.selectedPosition.value) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            executePendingBindings()
        }
    }


}