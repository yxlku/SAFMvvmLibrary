package com.deti.brand.demand.create.item.personinfo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPersonalInfoTipBinding
import com.test.common.RouterActivityPath
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle

class ItemPersonalInfoTip(
    var activty: Activity?
): QuickDataBindingItemBinder<ItemPersonalInfoEntity, BrandItemPersonalInfoTipBinding>() {

    var mViewMode = ItemPersonalViewModel(activty)
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPersonalInfoTipBinding>,
        data: ItemPersonalInfoEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewMolde = mViewMode
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPersonalInfoTipBinding {
        return BrandItemPersonalInfoTipBinding.inflate(layoutInflater, parent, false)
    }


}