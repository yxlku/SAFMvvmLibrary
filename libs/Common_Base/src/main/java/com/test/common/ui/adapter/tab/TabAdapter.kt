package com.test.common.ui.adapter.tab

import android.graphics.Color
import android.view.View
import android.widget.Adapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.safmvvm.utils.ResUtil
import com.test.common.R
import com.test.common.databinding.BaseItemCommonTabBinding

/**
 * 通用tab
 */
class TabAdapter(
    var block: (adapter: BaseQuickAdapter<*, *>,  view: View,position: Int, data: IAdapterTabEntity) -> Unit = {adapter: BaseQuickAdapter<*, *>,  view: View,position: Int, data: IAdapterTabEntity->}
): BaseQuickAdapter<IAdapterTabEntity, BaseDataBindingHolder<BaseItemCommonTabBinding>>(
    R.layout.base_item_common_tab
) {
    /** 是否显示删除*/
    var isShowDel: Boolean = false

    /** 选中位置*/
    var isSelectedPosition: Int = 0

    override fun convert(
        holder: BaseDataBindingHolder<BaseItemCommonTabBinding>,
        item: IAdapterTabEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            //删除按钮
            ivDel.visibility = if (isShowDel) {
                View.VISIBLE
            }else{
                View.GONE
            }
            ivDel.setOnClickListener{
                remove(item)
            }

            if (item.mode == IAdapterTabMode.MODE_LINE) {
                //选中状态
                vLine.visibility = if (isSelectedPosition == holder.adapterPosition) {
                    View.VISIBLE
                }else{
                    View.GONE
                }
            }else{
                //背景模式
                if (isSelectedPosition == holder.adapterPosition){
                    tvName.background = ResUtil.getDrawable(R.drawable.base_btn_yellow_bg_round)
                }else{
                    tvName.background = ResUtil.getDrawable(R.drawable.base_btn_tran_bg_round)
                }
            }
            //文字颜色
            if (isSelectedPosition == holder.adapterPosition){
                tvName.setTextColor(Color.parseColor("#1B2643"))
            }else{
                tvName.setTextColor(Color.parseColor("#666666"))
            }

            tvName.setOnClickListener {
                isSelectedPosition = holder.adapterPosition
                notifyDataSetChanged()
                block(this@TabAdapter, it, holder.adapterPosition, item)
            }

            executePendingBindings()
        }
    }
}