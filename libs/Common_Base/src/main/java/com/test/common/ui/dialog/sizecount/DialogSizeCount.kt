package com.test.common.ui.dialog.sizecount


import android.app.Activity
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.popup.createDialogBase

/**
 * 数量尺寸弹窗
 */
fun createDialogSizeCount(
    activity: Activity,
    title: String,
    datas: List<FirstNodeEntity> = arrayListOf(),
    block: (nodes: List<BaseNode>)->Unit = {}
): BasePopupView {
    return createDialogBase(
        SizeCountPopupView(activity, title, datas, block= block)
    ){

    }
}