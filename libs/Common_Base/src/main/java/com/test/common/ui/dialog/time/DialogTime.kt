package com.test.common.ui.dialog.time

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp

fun createDialogDate(
    activity: Activity,
    title: String,
    block: (millisecond: Long, time: String) -> Unit = {millisecond: Long, time: String->}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //如果不加这个，评论弹窗会移动到软键盘上面
    moveUpToKeyboard(false)
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(true)
}.asCustom(DatePopupView(activity, title, block))
