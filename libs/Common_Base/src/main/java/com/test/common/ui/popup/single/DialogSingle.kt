package com.test.common.ui.popup

import android.app.Activity
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupType
import com.safmvvm.app.BaseApp
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.single.DialogBottomSinglePopupView
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView

/****************************   项目中用到的所有单选弹窗    *******************************/

/**
 * 气泡单选
 */
fun List<BaseDialogSingleEntity>.dialogBubbleSingle(
    mActivity: Activity,
    v: View,
    /** 单选模式*/
    selectedMode: Int = DialogBubbleSinglePopupView.MODE_IMG,
    /** 默认选中位置*/
    selectedPosition: Int = -1,
    clickDismiss: Boolean,
    /** 布局宽度*/
    layoutWidth: Float = -1.0F,
    block: (view: View, position: Int, entity: BaseDialogSingleEntity)->Unit = {view: View, position: Int, entity: BaseDialogSingleEntity->}
): BasePopupView {
    return createDialogBase(
        DialogBubbleSinglePopupView(mActivity, v, selectedMode, selectedPosition, clickDismiss,this, layoutWidth, block)
    ){
        it.hasBlurBg(false)
        it.hasShadowBg(false)
        it.atView(v)
        it.popupType(PopupType.AttachView)
    }
}


/**
 * 通用底部单选弹窗
 */
fun List<BaseSingleChoiceEntity>.dialogBottomSingle(
    activity: Activity,
    title: String,
    /** 选中位置，优先级大于selectedBaseSingleChoiceEntity*/
    selectedPosition: Int = -1,
    /** 选中的实体类，传入后可以得到默认选中条目*/
    selectedBaseSingleChoiceEntity: BaseSingleChoiceEntity? = BaseSingleChoiceEntity(),
    selectedIsDismiss: Boolean = true,
    block: () -> Unit = {},
    /** 选中条目点击回调*/
    callback: (entity: BaseSingleChoiceEntity, position: Int) -> Unit,
    /** 右侧按钮点击事件*/
    rightClick: (view: View?) -> Unit = {}
): BasePopupView {
    return createDialogBase(
        DialogBottomSinglePopupView(activity, title, this,selectedBaseSingleChoiceEntity,  selectedPosition, selectedIsDismiss = selectedIsDismiss, callback = callback, rightClick = rightClick)
    ){
        block()
    }
}
