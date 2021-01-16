package com.deti.brand.demand.sampleclothes.all.adapter

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.ViewCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.demand.sampleclothes.all.adapter.SimpleClothesListAllAdapter.Companion.EXPAND_COLLAPSE_PAYLOAD
import com.deti.brand.demand.sampleclothes.all.entity.FirstNodeEntity

class FirstNodeProvider: BaseNodeProvider() {

    override val itemViewType: Int = 1

    override val layoutId: Int = R.layout.brand_item_simple_clothes_list_first
    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as FirstNodeEntity

        var tv_order_num = helper.getView<TextView>(R.id.tv_order_num)
        var tv_style_num = helper.getView<TextView>(R.id.tv_style_num)
        tv_order_num.text = "订单：${data.orderNumText}"
        tv_style_num.text = "款式：data.styleCount"
    }

    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        var data = item as FirstNodeEntity
        for (payload in payloads) {
            if (payload is Int && payload == EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, data, true)
            }
        }
    }

    private fun setArrowSpin(
        helper: BaseViewHolder,
        data: BaseNode,
        isAnimate: Boolean,
    ) {
        var imageView = helper.getView<ImageView>(R.id.iv_retract)
        val entity = data as FirstNodeEntity
        if (entity.isExpanded) {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(0f)
                    .start()
            } else {
                imageView.rotation = 0f
            }
        } else {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(180f)
                    .start()
            } else {
                imageView.rotation = 180f
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        getAdapter()!!.expandOrCollapse(position,
            true,
            true,
            EXPAND_COLLAPSE_PAYLOAD)

    }
}