package com.test.common.ui.dialog.pic

import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.entity.LocalMediaFolder
import com.test.common.R
import com.test.common.databinding.BaseDialogPhotoSelectItemBinding
import me.jessyan.autosize.utils.AutoSizeUtils

class DialogPhotSelectItemAdapter: BaseQuickAdapter<PhotoSelectEntity, BaseDataBindingHolder<BaseDialogPhotoSelectItemBinding>>(
    R.layout.base_dialog_photo_select_item
){

    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogPhotoSelectItemBinding>,
        item: PhotoSelectEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            var whScale: Int = item.localMedia?.height!! / AutoSizeUtils.mm2px(context, 162F)
            holder.itemView.layoutParams.apply {
                if (whScale != 0) {
                    width = item.localMedia?.width!! / whScale
                }
            }
            if (item.isSelect) {
                ivCheck.setImageResource(R.drawable.picture_icon_sel)
            }else{
                ivCheck.setImageResource(R.drawable.picture_icon_def)
            }

            executePendingBindings()
        }
    }
}