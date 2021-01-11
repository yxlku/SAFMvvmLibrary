package com.test.common.ui.dialog.single

import android.app.Activity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.BottomListPopupView
import com.safmvvm.app.BaseApp

/**
 * 通用底部弹窗-单选
 */
fun List<String>.create(
    title: String,
    block: (position: Int, text: String) -> Unit
): BottomListPopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    isDestroyOnDismiss(false) //对于只使用一次的弹窗，推荐设置这个
}.asBottomList(
    title,
    this.toTypedArray(),
    null, 2
) { position, text ->
    block(position, text)
}


fun List<BaseSingleChoiceEntity>.createDialog(
    activity: Activity,
    title: String,
    Datas: List<BaseSingleChoiceEntity> = arrayListOf(),
    block: () -> Unit = {}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //如果不加这个，评论弹窗会移动到软键盘上面
    moveUpToKeyboard(false)
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(false)

}.apply {
    block()
}.asCustom(SingleStringPopupView(activity, title, this))



