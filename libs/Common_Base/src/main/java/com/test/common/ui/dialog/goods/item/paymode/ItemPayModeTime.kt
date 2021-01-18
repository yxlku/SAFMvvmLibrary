package com.test.common.ui.dialog.goods.item.paymode

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.test.common.databinding.BaseDialogItemGoodsDetailPaymodeTimeBinding

class ItemPayModeTime: QuickDataBindingItemBinder<ItemPayModeTimeEntity, BaseDialogItemGoodsDetailPaymodeTimeBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseDialogItemGoodsDetailPaymodeTimeBinding>,
        data: ItemPayModeTimeEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseDialogItemGoodsDetailPaymodeTimeBinding = BaseDialogItemGoodsDetailPaymodeTimeBinding.inflate(layoutInflater, parent, false)
}