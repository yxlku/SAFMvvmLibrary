package com.test.common.ui.dialog.tip

import android.app.Activity
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import me.jessyan.autosize.utils.AutoSizeUtils

/**
 * 气泡弹窗
 */
fun String.createDialogTip(
    activity: Activity,
    view: View,
    hasShadowBg: Boolean = false,
    block: (builder: XPopup.Builder)->Unit = {}
): BasePopupView {
    return XPopup.Builder(activity)
        .hasShadowBg(hasShadowBg)
        .atView(view)
        .apply {
            block(this)
        }
        .asCustom(TipAttachPopupView(activity, this))
}