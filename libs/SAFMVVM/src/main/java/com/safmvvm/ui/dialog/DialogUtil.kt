package com.safmvvm.ui.dialog

import android.content.Context
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.ConfirmPopupView
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.safmvvm.app.globalconfig.GlobalConfig

/**
 * 弹窗工具，方便替换
 */
class DialogUtil(
    var context: Context,
    /** 是否点击确认取消时直接关闭弹窗*/
    var autoDismiss: Boolean = true,
    /** 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新*/
    var dismissOnBackPressed: Boolean = true,
    /** 点击外部是否关闭弹窗，默认为true，主要看是否强制更新 */
    var dismissOnTouchOutside: Boolean = true
) {

    /**
     * 通用弹窗配置
     */
    private fun commonDialog(): XPopup.Builder {
        var builder: XPopup.Builder = XPopup.Builder(context)
            .hasBlurBg(GlobalConfig.Dialog.gIsBlurBg) //高斯模糊
            .dismissOnBackPressed(dismissOnBackPressed) // 按返回键是否关闭弹窗，默认为true , 主要看是否强制更新
            .dismissOnTouchOutside(dismissOnTouchOutside) // 点击外部是否关闭弹窗，默认为true，主要看是否强制更新
            .autoDismiss(autoDismiss)
        return builder
    }

    /** 自定义弹窗view */
    fun customDialog(popupView: BasePopupView): BasePopupView{
       return commonDialog()
                .asCustom(popupView)
    }

    /**
     * 确认和取消弹窗
     */
    fun confirmAndCancelDialog(
        /** 标题文字*/
        title: String = "",
        /** 提示内容文字*/
        content: String = "",
        /** 取消按钮文字*/
        cancelText: String = "",
        /** 确认按钮文字*/
        confirmText: String = "",
        /** 取消事件操作*/
        cancelBlock: () -> Unit = {},
        /** 确认事件监听*/
        confirmBlock: () -> Unit = {},
        /** 是否隐藏取消按钮，默认不取消*/
        isHideCancel: Boolean = false
    ): ConfirmPopupView {
        return commonDialog()
            .asConfirm(
                title,
                content,
                cancelText,
                confirmText,
                object:OnConfirmListener{
                    override fun onConfirm() {
                        //确认
                        confirmBlock()
                    }

                } ,
                object: OnCancelListener{
                    override fun onCancel() {
                        //取消
                        cancelBlock()
                    }

                },
                isHideCancel //是否隐藏
            )
    }

}