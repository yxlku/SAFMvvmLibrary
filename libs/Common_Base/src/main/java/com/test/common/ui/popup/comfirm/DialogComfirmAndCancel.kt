package com.test.common.ui.popup.comfirm

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.CenterPopupView
import com.test.common.ui.popup.createDialogBase


/**
 * 确定取消按钮弹窗
 */
fun dialogComfirmAndCancel(
    mActivity: Activity,
    mTitle: String? = "温馨提示",
    mContent: String? = "",
    mLeftText: String? = "取消",
    mRightText: String? = "确定",
    /** 左侧文字颜色*/
    mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    mLeftClickBlock: (view: View, pop: CenterPopupView) -> Unit = { view: View, pop: CenterPopupView -> },
    mRightClickBlock: (view: View, pop: CenterPopupView) -> Unit = { view: View, pop: CenterPopupView -> },
): BasePopupView {
    return createDialogBase(
        ComfirmAndCancelPopupView(
            mActivity,
            mTitle,
            mContent,
            mLeftText,
            mRightText,
            mLeftBtnColor,
            mRightBtnColor,
            mLeftClickBlock,
            mRightClickBlock
        )
    )
}


/**
 * 确定取消按钮弹窗
 */
fun dialogComfirmAndCancelInput(
    mActivity: Activity,
    mTitle: String? = "温馨提示",
    mTipOne: String? = "",
    mLeftText: String? = "取消",
    mRightText: String? = "确定",
    /** 左侧文字颜色*/
    mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    mLeftClickBlock:  (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
    mRightClickBlock:  (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
): BasePopupView {
    return createDialogBase(
        ComfirmAndCancelInputPopupView(
            mActivity,
            mTitle,
            mTipOne,
            mLeftText,
            mRightText,
            mLeftBtnColor,
            mRightBtnColor,
            mLeftClickBlock,
            mRightClickBlock
        )
    )
}
/**
 * 确定取消按钮弹窗
 */
fun dialogComfirmAndCancelRemark(
    mActivity: Activity,
    mTitle: String? = "温馨提示",
    mTipOne: String? = "",
    mTipTwo: String? = "",
    mLeftText: String? = "取消",
    mRightText: String? = "确定",
    /** 左侧文字颜色*/
    mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    mLeftClickBlock:  (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
    mRightClickBlock:  (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
): BasePopupView {
    return createDialogBase(
        ComfirmAndCancelRemarkPopupView(
            mActivity,
            mTitle,
            mTipOne,
            mTipTwo,
            mLeftText,
            mRightText,
            mLeftBtnColor,
            mRightBtnColor,
            mLeftClickBlock,
            mRightClickBlock
        )
    )
}