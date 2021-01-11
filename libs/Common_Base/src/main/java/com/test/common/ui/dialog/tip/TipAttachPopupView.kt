package com.test.common.ui.dialog.tip

import android.content.Context
import android.widget.TextView
import com.lxj.xpopup.core.AttachPopupView
import com.test.common.R

class TipAttachPopupView(
    context: Context,
    var tipText: String
): AttachPopupView(context) {
    override fun getImplLayoutId(): Int = R.layout.base_dialog_tip
    override fun onCreate() {
        super.onCreate()
        var tv_text: TextView = findViewById(R.id.tv_text)

        tv_text.text = tipText

    }
}