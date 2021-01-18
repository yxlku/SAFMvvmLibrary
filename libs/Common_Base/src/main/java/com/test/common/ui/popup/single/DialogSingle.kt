package com.test.common.ui.popup

import android.app.Activity
import android.view.View
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupType
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView

/****************************   项目中用到的所有单选弹窗    *******************************/

fun List<BaseDialogSingleEntity>.dialogBubbleSingle(
    mActivity: Activity,
    v: View,
    /** 单选模式*/
    selectedMode: Int = DialogBubbleSinglePopupView.MODE_IMG,
    /** 默认选中位置*/
    selectedPosition: Int = -1,
    clickDismiss: Boolean,
    block: (view: View, position: Int, entity: BaseDialogSingleEntity)->Unit = {view: View, position: Int, entity: BaseDialogSingleEntity->}
): BasePopupView {
    return createDialogBase(
        DialogBubbleSinglePopupView(mActivity, v, selectedMode, selectedPosition, clickDismiss,this, block)
    ){
        it.atView(v)
        it.popupType(PopupType.AttachView)
    }
}