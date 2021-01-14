package com.deti.brand.demand.create.item.pic

import android.app.Activity
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPicChooseItemBinding

class ItemPicChooseItemAdapter(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter
): BaseQuickAdapter<ItemPicChooseItemEntity, BaseDataBindingHolder<BrandItemPicChooseItemBinding>>(
    R.layout.brand_item_pic_choose_item
) {


    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPicChooseItemBinding>,
        item: ItemPicChooseItemEntity,
    ) {
        var mViewModel = ItemPicChooseItemViewModel(mActivity, this)
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewModel
            executePendingBindings()
        }
    }

}