package com.test.common.ui.popup

import android.app.Activity
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupType
import com.safmvvm.app.BaseApp
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.SingleStringPopupView
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.single.DialogBottomSinglePopupView
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
    /** 布局宽度*/
    layoutWidth: Float = -1.0F,
    block: (view: View, position: Int, entity: BaseDialogSingleEntity)->Unit = {view: View, position: Int, entity: BaseDialogSingleEntity->}
): BasePopupView {
    return createDialogBase(
        DialogBubbleSinglePopupView(mActivity, v, selectedMode, selectedPosition, clickDismiss,this, layoutWidth, block)
    ){
        it.hasBlurBg(false)
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
    selectedPosition: Int = -1,
    selectedIsDismiss: Boolean = true,
    block: () -> Unit = {},
    /** 选中条目点击回调*/
    callback: (entity: BaseSingleChoiceEntity, position: Int) -> Unit,
    /** 右侧按钮点击事件*/
    rightClick: (view: View?) -> Unit = {}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //如果不加这个，评论弹窗会移动到软键盘上面
    moveUpToKeyboard(false)
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(false)

}.apply {
    block()
}.asCustom(DialogBottomSinglePopupView(activity, title, this,  selectedPosition, selectedIsDismiss = selectedIsDismiss, callback = callback, rightClick = rightClick))
