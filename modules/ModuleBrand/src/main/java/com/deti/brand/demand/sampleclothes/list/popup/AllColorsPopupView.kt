package com.deti.brand.demand.sampleclothes.list.popup

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.lxj.xpopup.core.BottomPopupView

/**
 * 齐色弹窗
 */
class AllColorsPopupView(
    var mActivity: Activity
) : BottomPopupView(mActivity){

    override fun getImplLayoutId(): Int = R.layout.brand_popup_all_colors

    var mAdapter = BaseBinderAdapter()
    override fun onCreate() {
        super.onCreate()
        var rv_content = findViewById<RecyclerView>(R.id.rv_content)

        mAdapter.apply {
//            addItem
        }
        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }


    }
}