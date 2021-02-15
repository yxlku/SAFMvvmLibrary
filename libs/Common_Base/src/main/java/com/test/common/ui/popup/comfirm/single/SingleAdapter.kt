package com.test.common.ui.popup.comfirm.single

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemSingleBinding

/**
 * 单选
 */
class SingleAdapter(
    var selectBlock: (view: View, adapter: SingleAdapter, position: Int, selectEntity: SingleEntity) -> Unit = {view: View, adapter: SingleAdapter, position: Int, entity: SingleEntity->}
): BaseQuickAdapter<SingleEntity, BaseDataBindingHolder<BaseDialogItemSingleBinding>>(
    R.layout.base_dialog_item_single
) {

    var selectionPosition = 0

    override fun convert(
        holder: BaseDataBindingHolder<BaseDialogItemSingleBinding>,
        item: SingleEntity,
    ) {
        holder.dataBinding?.apply {
            eneity = item
            if (selectionPosition == holder.adapterPosition) {
                ivImg.setImageResource(R.mipmap.base_dialog_single_selected)
            }else{
                ivImg.setImageResource(R.mipmap.base_dialog_single_unselected)
            }
            holder.itemView.setOnClickListener {
                selectionPosition = holder.adapterPosition
                notifyDataSetChanged()
                //选中的回调
                selectBlock(it, this@SingleAdapter, holder.adapterPosition, item)
            }
            executePendingBindings()
        }

    }
}