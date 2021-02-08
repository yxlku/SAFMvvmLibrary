package com.test.common.ui.popup.multiple

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp
import com.test.common.ui.popup.multiple.adapter.MultipleChoiceAdapter

/**
 * 通用多选
 */
fun List<BaseMultipleChoiceEntity>.createDialogSelectedMultiple(
    activity: Activity,
    title: String,
    block: () -> Unit = {},
    /** 是否显示标题栏tip*/
    isShowTip: Boolean = false,
    /** 标题栏tip点击事件*/
    tipBlock: (basePopupView: BasePopupView, view: View) -> Unit = { basePopupView: BasePopupView, view: View -> },
    callback: ((buttonView: CompoundButton?, isChecked: Boolean, entity: BaseMultipleChoiceEntity) -> Unit?)? = null,
    sureBlock: (basePopupView: BasePopupView, selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter) -> Unit = {basePopupView: BasePopupView,  selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter ->}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //如果不加这个，评论弹窗会移动到软键盘上面
    moveUpToKeyboard(false)
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(false)
}.apply {
    block()
}.asCustom(MultipleStringPoputView(activity, title, this, isShowTip = isShowTip, tipBlock = tipBlock, itemCallback = callback, sureBlock = sureBlock))



