package com.test.common.ui.dialog.sizecount


import android.app.Activity
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView
import com.safmvvm.app.BaseApp
import com.test.common.entity.CommonColorEntity
import com.test.common.ui.dialog.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.popup.createDialogBase

/**
 * 数量尺寸弹窗
 */
fun createDialogSizeCount(
    activity: Activity,
    title: String,
    datas: List<FirstNodeEntity> = arrayListOf(),
    block: (adapter: SizeCountAdapter, resultData:ArrayList<CommonColorEntity>, resultText: String, popupView: BottomPopupView)->Unit = {adapter: SizeCountAdapter, resultData:ArrayList<CommonColorEntity>,  resultText: String, popupView: BottomPopupView ->}
): BasePopupView {
    return createDialogBase(
        SizeCountPopupView(activity, title, datas, block= block)
    ){

    }
}