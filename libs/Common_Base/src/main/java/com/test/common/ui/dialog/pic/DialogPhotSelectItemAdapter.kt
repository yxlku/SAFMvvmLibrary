package com.test.common.ui.dialog.pic

import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.entity.LocalMediaFolder
import com.test.common.R
import com.test.common.databinding.BaseDialogPhotoSelectItemBinding

class DialogPhotSelectItemAdapter: BaseQuickAdapter<LocalMedia, BaseDataBindingHolder<BaseDialogPhotoSelectItemBinding>>(
    R.layout.base_dialog_photo_select_item
){
    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogPhotoSelectItemBinding>,
        item: LocalMedia,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()
        }
    }
}