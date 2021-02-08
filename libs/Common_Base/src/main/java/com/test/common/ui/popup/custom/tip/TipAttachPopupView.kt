package com.test.common.ui.popup.custom.tip

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.TextView
import com.lxj.xpopup.core.AttachPopupView
import com.test.common.R
import com.xujiaji.happybubble.BubbleLayout

class TipAttachPopupView(
    context: Context,
    var view: View,
    var tipText: String,
): AttachPopupView(context) {
    override fun getImplLayoutId(): Int = R.layout.base_dialog_tip
    override fun onCreate() {
        super.onCreate()
        var tv_text: TextView = findViewById(R.id.tv_text)
        var bl_layout: BubbleLayout = findViewById(R.id.bl_layout)

        tv_text.text = tipText

        val clickedViewLocation = IntArray(2)
        view.getLocationOnScreen(clickedViewLocation)
        bl_layout.lookPosition = clickedViewLocation[0]
    }
}