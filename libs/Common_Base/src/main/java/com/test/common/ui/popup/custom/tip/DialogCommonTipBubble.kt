package com.test.common.ui.popup.custom.tip

import android.app.Activity
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.util.XPopupUtils
import me.jessyan.autosize.utils.AutoSizeUtils

/*************************自定义dialog： 所有气泡类型的创建方法都放在此类中*******************************/


/**
 * 气泡弹窗：标题栏提示气泡
 */
fun String.createDialogTitleTip(
    activity: Activity,
    view: View,
    hasShadowBg: Boolean = false,
    block: (builder: XPopup.Builder) -> Unit = {},
): BasePopupView {
    return XPopup.Builder(activity)
        .hasShadowBg(hasShadowBg)
        .maxWidth(XPopupUtils.getWindowWidth(activity) - AutoSizeUtils.mm2px(activity, 50F))
        .atView(view)
        .isDestroyOnDismiss(true)
        .apply {

            block(this)
        }
        .asCustom(TipAttachPopupView(activity, view,this))
}
fun String.createDialogTitleTipBottom(
    activity: Activity,
    view: View,
    hasShadowBg: Boolean = false,
    block: (builder: XPopup.Builder) -> Unit = {},
): BasePopupView = createDialogTitleTip(activity, view, hasShadowBg){builder: XPopup.Builder->
    builder.offsetY(AutoSizeUtils.mm2px(activity, -15F))
    builder.offsetX(AutoSizeUtils.mm2px(activity, 15F))
}