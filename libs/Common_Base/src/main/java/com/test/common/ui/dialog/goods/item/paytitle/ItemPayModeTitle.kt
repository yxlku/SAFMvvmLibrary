package com.test.common.ui.dialog.goods.item.paytitle

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.test.common.databinding.BaseDialogItemGoodsDetailPaymodeTitleBinding

class ItemPayModeTitle: QuickViewBindingItemBinder<ItemPayModeTitleEntity, BaseDialogItemGoodsDetailPaymodeTitleBinding>() {
    override fun convert(
        holder: BinderVBHolder<BaseDialogItemGoodsDetailPaymodeTitleBinding>,
        data: ItemPayModeTitleEntity,
    ) {
        var binding = holder.viewBinding
        binding?.apply {
            executePendingBindings()
        }
    }

    override fun onCreateViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseDialogItemGoodsDetailPaymodeTitleBinding = BaseDialogItemGoodsDetailPaymodeTitleBinding.inflate(layoutInflater, parent, false)
}