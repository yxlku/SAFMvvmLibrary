package com.test.common.ui.popup.single

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.test.common.R
import com.test.common.databinding.BaseItemDialogBaseBubbleSingleBinding
import com.test.common.ui.popup.base.BaseDialogSingleEntity

class DialogBubbleSingleAdapter(
    var selectedPosition: Int = -1,
    /** 选中模式*/
    var mChooseMode: Int = DialogBubbleSinglePopupView.MODE_IMG,
): BaseQuickAdapter<BaseDialogSingleEntity, BaseDataBindingHolder<BaseItemDialogBaseBubbleSingleBinding>>(
        R.layout.base_item_dialog_base_bubble_single
    ) {


    override fun convert(
        holder: BaseDataBindingHolder<BaseItemDialogBaseBubbleSingleBinding>,
        item: BaseDialogSingleEntity,
    ) {
        holder.dataBinding?.apply {
            entity = item
            var isChoose = holder.adapterPosition == selectedPosition
            //图片模式
            isShowModeImg(ivChooseState, isChoose)
            //背景模式
            isShowModeBg(llBg, tvText, isChoose)
            executePendingBindings()
        }
    }

    /**
     * 图片模式
     */
    fun isShowModeImg(view: ImageView, isChoose: Boolean){
        if (mChooseMode == DialogBubbleSinglePopupView.MODE_IMG || mChooseMode == DialogBubbleSinglePopupView.MODE_ALL) {
            if (isChoose) {
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.GONE
            }
        }else{
            view.visibility = View.GONE
        }
    }
    /**
     * 背景模式
     */
    fun isShowModeBg(view: ViewGroup, tv: TextView, isChoose: Boolean){
        if (mChooseMode == DialogBubbleSinglePopupView.MODE_BG || mChooseMode == DialogBubbleSinglePopupView.MODE_ALL) {
            if (isChoose) {
                view.setBackgroundColor(Color.parseColor("#282B38"))
                tv.setTextColor(Color.parseColor("#FCCE48"))
            }else{
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                tv.setTextColor(Color.parseColor("#333333"))
            }
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }
}