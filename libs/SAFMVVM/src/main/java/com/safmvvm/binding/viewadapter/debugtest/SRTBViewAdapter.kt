package com.safmvvm.binding.viewadapter.debugtest

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.safmvvm.ui.theme.StatusBarUtil
import com.scwang.smart.refresh.layout.SmartRefreshLayout

object SRTBViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["Debug_isDamp2", "Debug_isTitleBar"], requireAll = false)
    fun addTest(
        view: View,
        isDamp: Boolean,
        isTitleBar: Boolean
    ) {
//        if (isDamp || isTitleBar) {
//            var constraintLayout = ConstraintLayout(view.context)
//            constraintLayout.layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//
////            linearLayout.orientation = LinearLayout.VERTICAL
//
//
////            if (isTitleBar) {
////                var titleBar = TitleBar(view.context)
////                titleBar.layoutParams = LinearLayout.LayoutParams(
////                    LinearLayout.LayoutParams.MATCH_PARENT,
////                    LinearLayout.LayoutParams.WRAP_CONTENT
////                )
////                linearLayout.addView(titleBar)
////            }
//            var titleBar = StatusBarUtil.obtainTitleBar(view)
//            var parent: ViewGroup = view.parent as ViewGroup
//            parent?.let {
//                it.removeView(view)
//            }
//            if (isDamp) {
//
//
//                var smartRefreshLayout = SmartRefreshLayout(view.context)
//                smartRefreshLayout.setEnableLoadMore(false)
//                smartRefreshLayout.setEnableRefresh(false)
//                smartRefreshLayout.setEnableOverScrollDrag(true)
//                smartRefreshLayout.setEnableNestedScroll(true)
//                smartRefreshLayout.addView(view)
//                (titleBar?.parent as ViewGroup).removeView(titleBar)
//                constraintLayout.addView(titleBar)
//                constraintLayout.addView(smartRefreshLayout)
//
//                titleBar?.let{
//                    it.layoutParams =
//                        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
//                            .apply {
//                                topToTop = view.top
//                                startToStart = view.left
//                                endToEnd = view.right
//                                bottomToTop = smartRefreshLayout.top
//                            }
//                }
//
//                smartRefreshLayout.layoutParams =
//                    ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
//                        .apply {
//                            titleBar?.let {
//                                topToBottom = titleBar.bottom
//                            } ?: {
//                                topToTop = view.top
//                            }
//                            leftToLeft = view.left
//                            rightToRight = view.right
//                            bottomToBottom = (view.parent as ViewGroup).bottom
//
//                        }
//
//            } else {
//                constraintLayout.addView(view)
//            }
//
//            parent.addView(constraintLayout)
//
//
//        }

    }

}