package com.deti.designer.style.popup.item.radio

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemStyleEditRadioItemBinding

class ItemRadioAdapter: BaseQuickAdapter<ItemRadioData, BaseDataBindingHolder<DesignerItemStyleEditRadioItemBinding>>(
    R.layout.designer_item_style_edit_radio_item
) {

    var checkPosition = -1

    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemStyleEditRadioItemBinding>,
        item: ItemRadioData,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            if (holder.adapterPosition == checkPosition) {
                ivRadio.setImageResource(R.mipmap.designer_style_edit_radio_selected)
            }else{
                ivRadio.setImageResource(R.mipmap.designer_style_edit_radio_unselect)
            }
            executePendingBindings()
        }
    }
}