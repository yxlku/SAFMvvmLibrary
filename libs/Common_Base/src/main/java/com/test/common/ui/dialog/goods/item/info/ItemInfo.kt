package com.test.common.ui.dialog.goods.item.info

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.lxj.xpopup.core.BasePopupView
import com.test.common.databinding.BaseDialogItemGoodsDetailInfoBinding
import com.test.common.ui.popup.time.createDialogDate

class ItemInfo(
    var mActivity: Activity
): QuickViewBindingItemBinder<ItemInfoEntity, BaseDialogItemGoodsDetailInfoBinding>() {
    override fun convert(
        holder: BinderVBHolder<BaseDialogItemGoodsDetailInfoBinding>,
        data: ItemInfoEntity,
    ) {
        var binding = holder.viewBinding
        binding.apply {
            entity = data
            executePendingBindings()
        }
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (data.tagId == ItemInfoTagIds.ID_GOODS_TIME) {
                    createDialogDate(mActivity, "请选择时间"){millisecond: Long, time: String, popupView: BasePopupView->
                        binding.entity?.content = time
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }
        })
    }

    override fun onCreateViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseDialogItemGoodsDetailInfoBinding = BaseDialogItemGoodsDetailInfoBinding.inflate(layoutInflater, parent, false)
}