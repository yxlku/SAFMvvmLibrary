package com.safmvvm.binding.viewadapter.debugtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.safmvvm.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

object SRViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["Debug_isDamp"], requireAll = false)
    fun addTest(
        view: View,
        isDamp: Boolean
    ){
//        if (isDamp) {
//            if (view is ViewGroup) {
//                var srView: ViewGroup = LayoutInflater.from(view.context).inflate(R.layout.rv,null) as ViewGroup
//                var parent: ViewGroup = view.parent as ViewGroup
//                var smartRefreshLayout: SmartRefreshLayout = srView.findViewById(R.id.dataBinding_srl_layout)
//                parent.let{
//                    it.removeView(view)
//                }
//                smartRefreshLayout.addView(view)
//                parent.addView(smartRefreshLayout)
//            }else{
//                throw(RuntimeException("app:isDamp=@{true} 只支持在ViewGroup下"))
//            }
//
//        }

    }

}