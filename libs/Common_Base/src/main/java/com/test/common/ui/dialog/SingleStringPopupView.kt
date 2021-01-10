package com.test.common.ui.dialog

import android.app.Activity
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.app.BaseApp
import com.test.common.R

class SingleStringPopupView(
    var activit: Activity,
    var mTitle: String = "",
    var mHeightMultiple: Float = 0.7F
): BottomPopupView(activit) {

    override fun getImplLayoutId(): Int = R.layout.base_dialog_list
    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int = (XPopupUtils.getScreenHeight(activit) * mHeightMultiple).toInt()


    override fun onCreate() {
        super.onCreate()

        var tv_title: TextView = findViewById(R.id.tv_title)
        tv_title.setText(mTitle)
    }




}