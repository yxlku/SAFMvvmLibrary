package com.safmvvm.binding.viewadapter.debugtest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.safmvvm.ui.titlebar.TitleBar
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class TbConstraintLayout: ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        linearLayout = LinearLayout(context)
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL

        titleBar = TitleBar(context, attrs)
        titleBar.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        smartRefreshLayout = SmartRefreshLayout(context)
        smartRefreshLayout.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
        smartRefreshLayout.setEnableLoadMore(false)
        smartRefreshLayout.setEnableRefresh(false)
        smartRefreshLayout.setEnableOverScrollDrag(true)
        smartRefreshLayout.setEnableNestedScroll(true)

    }
    var smartRefreshLayout: SmartRefreshLayout
    var linearLayout: LinearLayout
    var titleBar: TitleBar

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var parent = parent as ViewGroup
        var child: View? = null
        var thisViewIndex = 0
        parent.let {
            for (i in 0 until parent.childCount ){
                if (parent.getChildAt(i) == this) {
                    child = parent.getChildAt(i)
                    thisViewIndex = i
                    break
                }
            }
        }
        linearLayout.addView(titleBar, 0)
        linearLayout.addView(smartRefreshLayout, 1)


        smartRefreshLayout.addView(rootView, 0)

//        parent.removeView(this)
//        smartRefreshLayout.addView(this)
    }

}