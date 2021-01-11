package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPicChooseBinding
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.multiple.createDialogSelectedMultiple

class ItemPicChoose(
    var mActivity:  Activity?
): QuickDataBindingItemBinder<ItemPicChooseEntity, BrandItemPicChooseBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPicChooseBinding>,
        data: ItemPicChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewModel = this
        binding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPicChooseBinding = BrandItemPicChooseBinding.inflate(layoutInflater, parent, false, null)


    fun btnShowMultipeDialog(v: View){
        mActivity?.let {
            var datas = arrayListOf<BaseMultipleChoiceEntity>().apply {
                for (i in 0 until 5){
                    if(i % 2 == 1) {
                        add(BaseMultipleChoiceEntity("text${i}", true))
                    }else{
                        add(BaseMultipleChoiceEntity("text${i}", false))
                    }
                }
            }
            datas.createDialogSelectedMultiple(it, "testTitle多选").show()
        }
    }
}