package com.safmvvm.ext.ui.typesview.adapter.two

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.ext.ui.R
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewThreeBinding
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewTwoBinding
import com.safmvvm.ext.ui.typesview.adapter.three.TypesThreeViewModel
import com.safmvvm.ext.ui.typesview.entity.TypesViewThreeEntity
import com.safmvvm.ext.ui.typesview.entity.TypesViewTwoEntity

class TypeThreeAdapter : BaseQuickAdapter<TypesViewThreeEntity, BaseDataBindingHolder<ExtuiItemTypesviewThreeBinding>>(
R.layout.extui_item_typesview_three
) {
    var mViewModel = TypesThreeViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<ExtuiItemTypesviewThreeBinding>,
        item: TypesViewThreeEntity,
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