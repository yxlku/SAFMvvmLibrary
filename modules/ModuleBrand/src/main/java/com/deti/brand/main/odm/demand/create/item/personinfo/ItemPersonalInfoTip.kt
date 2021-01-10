package com.deti.brand.main.odm.demand.create.item.personinfo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPersonalInfoTipBinding
import com.test.common.ui.dialog.create
import com.test.common.ui.dialog.createDialog

class ItemPersonalInfoTip(
    var activty: Activity?
): QuickDataBindingItemBinder<ItemPersonalInfoEntity, BrandItemPersonalInfoTipBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPersonalInfoTipBinding>,
        data: ItemPersonalInfoEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewMolde = this
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPersonalInfoTipBinding {
        return BrandItemPersonalInfoTipBinding.inflate(layoutInflater, parent, false)
    }

    fun clickDialogTest(v: View){
        activty?.let {
            arrayListOf("11", "222").createDialog(it, "testTitle").show()
        }
    }
}