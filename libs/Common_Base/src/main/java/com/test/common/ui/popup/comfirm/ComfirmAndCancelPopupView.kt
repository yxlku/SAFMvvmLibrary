package com.test.common.ui.popup.comfirm

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.lxj.xpopup.core.CenterPopupView
import com.test.common.R

/**
 * 确定取消弹窗
 */
class ComfirmAndCancelPopupView(
    var mActivity: Activity,
    var mTitle: String? = "温馨提示",
    var mContent: String? = "",
    var mLeftText: String? = "取消",
    var mRightText: String? = "确定",
    /** 左侧文字颜色*/
    var mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    var mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    var mLeftCancelBlock: (view: View, pop: CenterPopupView) -> Unit = {view: View, pop: CenterPopupView ->},
    var mRightCancelBlock: (view: View, pop: CenterPopupView) -> Unit = {view: View, pop: CenterPopupView ->},
) : CenterPopupView(mActivity){

    override fun getImplLayoutId(): Int = R.layout.base_dialog_comfirm_and_cancel

    override fun onCreate() {
        super.onCreate()

        var tv_title = findViewById<TextView>(R.id.tv_title)
        var tv_content = findViewById<TextView>(R.id.tv_content)
        var tv_btn_left = findViewById<TextView>(R.id.tv_btn_left)
        var tv_btn_right = findViewById<TextView>(R.id.tv_btn_right)

        tv_title.text = mTitle
        tv_content.text = mContent
        tv_btn_left.text = mLeftText
        tv_btn_right.text = mRightText

        tv_btn_left.setTextColor(mLeftBtnColor)
        tv_btn_right.setTextColor(mRightBtnColor)

        tv_btn_left.setOnClickListener {
            mLeftCancelBlock(it, this)
        }

        tv_btn_right.setOnClickListener {
            mRightCancelBlock(it, this)
        }


    }
}