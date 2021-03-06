package com.safmvvm.binding.viewadapter.elasticity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.safmvvm.R
import com.safmvvm.utils.LogUtil
import com.scwang.smart.refresh.layout.SmartRefreshLayout


@BindingAdapter(value = ["isDamp"], requireAll = false)
fun layoutIsDamp(
    view: View,
    isDamp: Boolean
) {
    if (isDamp) {
        if (view is ViewGroup) {
            var parent: ViewGroup = view.parent as ViewGroup
//                var srView: ViewGroup = LayoutInflater.from(view.context).inflate(R.layout.rv,null) as ViewGroup
//                var smartRefreshLayout: SmartRefreshLayout = srView.findViewById(R.id.dataBinding_srl_layout)
            var smartRefreshLayout = SmartRefreshLayout(view.context)
            smartRefreshLayout.layoutParams = view.layoutParams
            smartRefreshLayout.setEnableLoadMore(false)
            smartRefreshLayout.setEnableRefresh(false)
            smartRefreshLayout.setEnableOverScrollDrag(true)
            smartRefreshLayout.setEnableNestedScroll(true)

            parent.let {
                it.removeView(view)
            }
            smartRefreshLayout.addView(view)
            parent.addView(smartRefreshLayout)
        } else {
            throw(RuntimeException("app:isDamp=@{true} 只支持在ViewGroup下"))
        }

    }
}

