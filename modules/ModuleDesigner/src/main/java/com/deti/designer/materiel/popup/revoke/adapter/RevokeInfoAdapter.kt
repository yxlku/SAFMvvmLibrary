package com.deti.designer.materiel.popup.revoke.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemRemarkBinding
import com.deti.designer.databinding.DesignerItemRevokeInfoBinding

class RevokeInfoAdapter: BaseQuickAdapter<RevokeInfoEntity, BaseDataBindingHolder<DesignerItemRevokeInfoBinding>>(
    R.layout.designer_item_revoke_info
) {

    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemRevokeInfoBinding>,
        item: RevokeInfoEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            if (item.isChecked) {
                //选中
                ivCheck.setImageResource(R.drawable.base_dialog_checkbox_selected)
            }else{
                ivCheck.setImageResource(R.drawable.base_dialog_checkbox_unselect)
            }
            //选中
            ivCheck.setOnClickListener{
                item.isChecked = !item.isChecked
                notifyDataSetChanged()
            }
            executePendingBindings()
        }
    }
}