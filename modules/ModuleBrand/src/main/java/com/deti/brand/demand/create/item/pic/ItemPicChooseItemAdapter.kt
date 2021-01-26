package com.deti.brand.demand.create.item.pic

import android.app.Activity
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPicChooseItemBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.safmvvm.ui.autosize.AutoSizeUtil
import me.jessyan.autosize.utils.AutoSizeUtils
import me.jessyan.autosize.utils.ScreenUtils

class ItemPicChooseItemAdapter(
    var mViewModel: CreateDemandViewModel,
    var delBlock: (entity: ItemPicChooseItemEntity, pos: Int) -> Unit
): BaseQuickAdapter<ItemPicChooseItemEntity, BaseDataBindingHolder<BrandItemPicChooseItemBinding>>(
    R.layout.brand_item_pic_choose_item
) {

    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPicChooseItemBinding>,
        item: ItemPicChooseItemEntity,
    ) {

        var binding = holder.dataBinding
        binding?.apply {
            clOne.layoutParams.apply {
                var sc = ScreenUtils.getScreenSize(context)[0] - AutoSizeUtils.mm2px(context, 56F)
                if (holder.adapterPosition == 0) {
                    width = sc / 2
                }else{
                    width = sc / 4
                }
            }
            entity = item
            viewModel = mViewModel
            ivDel.setOnClickListener {
                delBlock(item, holder.adapterPosition)
            }
            executePendingBindings()
        }
    }

}