package com.safmvvm.ext.ui.typesview.adapter.first

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.ext.ui.R
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewFirstBinding
import com.safmvvm.ext.ui.typesview.entity.TypesViewEntity

class TypeFirstAdapter : BaseQuickAdapter<TypesViewEntity, BaseDataBindingHolder<ExtuiItemTypesviewFirstBinding>>(
R.layout.extui_item_typesview_first
) {
    var mViewModel = TypesFirstViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<ExtuiItemTypesviewFirstBinding>,
        item: TypesViewEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            if (holder.adapterPosition == mViewModel.selectedPosition.value) {
                cbItem.setBackgroundColor(Color.parseColor("#66E5E6E8"))
            } else {
                cbItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            executePendingBindings()
        }
    }
}