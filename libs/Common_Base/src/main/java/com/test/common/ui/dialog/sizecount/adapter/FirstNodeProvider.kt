package com.test.common.ui.dialog.sizecount.adapter

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.safmvvm.bus.LiveDataBus
import com.test.common.R
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class FirstNodeProvider(
    @LayoutRes var mLayoutId : Int = R.layout.base_dialog_item_sizecount_first
) : BaseNodeProvider() {

    override val itemViewType: Int = 1

    override val layoutId: Int = mLayoutId

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as FirstNodeEntity
        var tv_color = helper.getView<TextView>(R.id.tv_color)
        var tv_count = helper.getView<TextView>(R.id.tv_count)

        tv_color.text = data.color + "订单数量："
        tv_count.text = data.count.toString() + "件"
        LiveDataBus.observe(this, data.color, Observer {
            var count = 0
            data.childNode.forEach {
                var childEntity = it as SecondNodeEntity
                count += childEntity.count
            }
            data.count = count
            tv_count.text = data.count.toString() + "件"
        }, true)

    }

    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        var data = item as FirstNodeEntity
        for (payload in payloads) {
            if (payload is Int && payload == SizeCountAdapter.EXPAND_COLLAPSE_PAYLOAD) {
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
        var imageView = helper.getView<ImageFilterView>(R.id.iv_retract)
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
            SizeCountAdapter.EXPAND_COLLAPSE_PAYLOAD)
    }
}