package com.test.common.ui.popup

import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp


/**
 * 弹窗基类
 */
fun createDialogBase(
    customView: BasePopupView,
    block:(builder: XPopup.Builder) -> Unit = {}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(false)
    //自方法自定义属性
    block(this)
}.asCustom(customView)
