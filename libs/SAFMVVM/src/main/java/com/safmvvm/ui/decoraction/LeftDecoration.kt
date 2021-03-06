package  com.safmvvm.ui.decoraction

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: 左边距Decoration
 * Author : pxq
 * Date : 2020/7/31 8:02 PM
 */
class LeftDecoration(private val left : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter?.itemCount ?: 0
        // 第一个、最后一个都不处理
        when (position) {
            0 ->    // 不设置边距
                outRect.set(0, 0, 0, 0)
            else -> // 设置左边距
                outRect.set(left, 0, 0, 0)
        }

    }

}