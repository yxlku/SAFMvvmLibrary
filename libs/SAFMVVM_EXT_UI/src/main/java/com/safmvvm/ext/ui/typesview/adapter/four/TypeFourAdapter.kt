package com.safmvvm.ext.ui.typesview.adapter.four

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.ext.ui.R
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewFourBinding
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewThreeBinding
import com.safmvvm.ext.ui.databinding.ExtuiItemTypesviewTwoBinding
import com.safmvvm.ext.ui.typesview.adapter.three.TypesThreeViewModel
import com.safmvvm.ext.ui.typesview.entity.TypesViewFourEntity
import com.safmvvm.ext.ui.typesview.entity.TypesViewThreeEntity
import com.safmvvm.ext.ui.typesview.entity.TypesViewTwoEntity

class TypeFourAdapter : BaseQuickAdapter<TypesViewFourEntity, BaseDataBindingHolder<ExtuiItemTypesviewFourBinding>>(
R.layout.extui_item_typesview_four
) {
    var mViewModel = TypesFourViewModel()
    override fun convert(
        holder: BaseDataBindingHolder<ExtuiItemTypesviewFourBinding>,
        item: TypesViewFourEntity,
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