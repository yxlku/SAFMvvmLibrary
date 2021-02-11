package com.test.common.ui.popup.multiple

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp
import com.test.common.ui.popup.createDialogBase
import com.test.common.ui.popup.multiple.adapter.MultipleChoiceAdapter

/**
 * 通用多选
 */
fun List<BaseMultipleChoiceEntity>.createDialogSelectedMultiple(
    activity: Activity,
    title: String,
    /** 选中的数据*/
    mSelectIds: List<String> = arrayListOf(),
    block: (builder: XPopup.Builder) -> Unit = {},
    /** 是否显示标题栏tip*/
    isShowTip: Boolean = false,
    /** 标题栏tip点击事件*/
    tipBlock: (basePopupView: BasePopupView, view: View) -> Unit = { basePopupView: BasePopupView, view: View -> },
    sureBlock: (basePopupView: BasePopupView, selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter) -> Unit = {basePopupView: BasePopupView,  selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter ->}
): BasePopupView {
    return createDialogBase(
        MultipleStringPoputView(activity, title, this, mSelectIds, isShowTip = isShowTip, tipBlock = tipBlock, sureBlock = sureBlock)
    ){
        block(it)
    }
}



