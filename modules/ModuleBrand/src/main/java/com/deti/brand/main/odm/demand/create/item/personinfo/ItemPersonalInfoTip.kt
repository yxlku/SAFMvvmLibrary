package com.deti.brand.main.odm.demand.create.item.personinfo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPersonalInfoTipBinding
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle

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
            var datas = arrayListOf<BaseSingleChoiceEntity>().apply {
                for (i in 0 until 5){
                    add(BaseSingleChoiceEntity("text${i}"))
                }
            }
            datas.createDialogSelectedSingle(it, "testTitle", false).show()
        }
    }
}