package com.test.common.ui.dialog.morebtns

import android.content.Context
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.AttachListPopupView
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.R
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity



fun ArrayList<String>.createDialogSelectedSingleMoreTip(
    context: Context,
    v: View,
    block: (position: Int, text: String) -> Unit = {position: Int, text: String -> }
): AttachListPopupView {
    var attachPopupView = XPopup.Builder(context)
        .hasShadowBg(false) //                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
        .atView(v) // 依附于所点击的View，内部会自动判断在上方或者下方显示
        .asAttachList(this.toTypedArray(),
            intArrayOf(),
            { position, text ->
                block(position, text)
            },
            R.layout.base_dialog_tip_more,
            R.layout.base_dialog_tip_more_item)

    return attachPopupView
}
