package com.test.common.ui.dialog.goods.item.paymode

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.loper7.date_time_picker.StringUtils
import com.lxj.xpopup.core.BasePopupView
import com.test.common.databinding.BaseDialogItemGoodsDetailPaymodeTimeBinding
import com.test.common.ui.popup.time.createDialogDate
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.dialogBubbleSingle
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView

class ItemPayModeTime(
    var mActivity: Activity,
): QuickDataBindingItemBinder<ItemPayModeTimeEntity, BaseDialogItemGoodsDetailPaymodeTimeBinding>() {

    var testSelected = 0

    override fun convert(
        holder: BinderDataBindingHolder<BaseDialogItemGoodsDetailPaymodeTimeBinding>,
        data: ItemPayModeTimeEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            llTime.setOnClickListener {
                createDialogDate(mActivity, "请选择时间") { millisecond: Long, time: String, popupView: BasePopupView ->
                    binding?.entity?.time = StringUtils.conversionTime(millisecond, "yyyy-MM-dd")
                    adapter.notifyDataSetChanged()
                }.show()
            }
            llProportion.setOnClickListener{
                var listData = arrayListOf<BaseDialogSingleEntity>(
                    BaseDialogSingleEntity(0, "10%"),
                    BaseDialogSingleEntity(0, "20%"),
                    BaseDialogSingleEntity(0, "30%"),
                    BaseDialogSingleEntity(0, "40%"),
                    BaseDialogSingleEntity(0, "50%"),
                    BaseDialogSingleEntity(0, "60%"),
                    BaseDialogSingleEntity(0, "70%"),
                    BaseDialogSingleEntity(0, "80%"),
                    BaseDialogSingleEntity(0, "90%"),
                    BaseDialogSingleEntity(0, "100%"),
                )
                mActivity?.apply {
                    listData.dialogBubbleSingle(
                        this,
                        it,
                        DialogBubbleSinglePopupView.MODE_BG,
                        testSelected,
                        true,
                        200F,
                    ){ view: View, position: Int, entity: BaseDialogSingleEntity ->
                        testSelected = position
                        binding?.entity?.proportion = entity.text
                        adapter.notifyDataSetChanged()
                    }.show()
                }
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