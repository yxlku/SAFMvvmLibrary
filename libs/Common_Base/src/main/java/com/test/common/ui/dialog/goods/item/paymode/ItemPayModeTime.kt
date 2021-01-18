package com.test.common.ui.dialog.goods.item.paymode

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.loper7.date_time_picker.StringUtils
import com.test.common.databinding.BaseDialogItemGoodsDetailPaymodeTimeBinding
import com.test.common.ui.dialog.time.createDialogDate

class ItemPayModeTime(
    var mActivity: Activity,
): QuickDataBindingItemBinder<ItemPayModeTimeEntity, BaseDialogItemGoodsDetailPaymodeTimeBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<BaseDialogItemGoodsDetailPaymodeTimeBinding>,
        data: ItemPayModeTimeEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            llTime.setOnClickListener {
                createDialogDate(mActivity, "请选择时间") { millisecond: Long, time: String ->
                    binding?.entity?.time = StringUtils.conversionTime(millisecond, "yyyy-MM-dd")
                    adapter.notifyDataSetChanged()
                }.show()
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseDialogItemGoodsDetailPaymodeTimeBinding = BaseDialogItemGoodsDetailPaymodeTimeBinding.inflate(layoutInflater, parent, false)
}