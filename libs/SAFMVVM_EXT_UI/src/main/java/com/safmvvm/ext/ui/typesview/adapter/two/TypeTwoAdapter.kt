package com.safmvvm.ext.ui.typesview.adapter.two

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.ext.ui.R
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewTwoBinding
import com.safmvvm.ext.ui.typesview.entity.TypesViewTwoEntity

class TypeTwoAdapter : BaseQuickAdapter<TypesViewTwoEntity, BaseDataBindingHolder<ExtuiItemTypesviewTwoBinding>>(
R.layout.extui_item_typesview_two
) {
    var mViewModel = TypesTwoViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<ExtuiItemTypesviewTwoBinding>,
        item: TypesViewTwoEntity,
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