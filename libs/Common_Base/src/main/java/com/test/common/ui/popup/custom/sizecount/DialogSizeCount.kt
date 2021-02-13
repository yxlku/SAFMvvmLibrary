package com.test.common.ui.popup.custom.sizecount


import android.app.Activity
import com.lxj.xpopup.core.BasePopupView
import com.test.common.entity.CommonColorEntity
import com.test.common.ui.popup.custom.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.popup.custom.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.popup.createDialogBase

/**
 * 数量尺寸弹窗
 */
fun createDialogSizeCount(
    activity: Activity,
    title: String,
    datas: List<FirstNodeEntity> = arrayListOf(),
    colorSizeCountDatas: ArrayList<CommonColorEntity> = arrayListOf(),
    block: (adapter: SizeCountAdapter, resultData:ArrayList<CommonColorEntity>, resultText: String, popupView: BasePopupView)->Unit = {adapter: SizeCountAdapter, resultData:ArrayList<CommonColorEntity>,  resultText: String, popupView: BasePopupView ->}
): BasePopupView {
    return createDialogBase(
        SizeCountPopupView(activity, title, datas,colorSizeCountDatas, block= block)
    ){
//        it.autoOpenSoftInput(true)
        //如果不加这个，评论弹窗会移动到软键盘上面
        it.moveUpToKeyboard(false)
        it.autoFocusEditText(false)
    }
}