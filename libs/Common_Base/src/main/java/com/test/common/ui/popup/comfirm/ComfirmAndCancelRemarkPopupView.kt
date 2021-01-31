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
class ComfirmAndCancelRemarkPopupView(
    var mActivity: Activity,
    var mTitle: String? = "温馨提示",
    var mTipOne: String? = "",
    var mTipTwo: String? = "",
    var mLeftText: String? = "取消",
    var mRightText: String? = "确定",
    /** 左侧文字颜色*/
    var mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    var mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    var mLeftCancelBlock: (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
    var mRightCancelBlock: (view: View, pop: CenterPopupView, inputText: String) -> Unit = {view: View, pop: CenterPopupView, inputText: String ->},
) : CenterPopupView(mActivity){

    override fun getImplLayoutId(): Int = R.layout.base_dialog_comfirm_and_cancel_remark

    override fun onCreate() {
        super.onCreate()

        var tv_title = findViewById<TextView>(R.id.tv_title)
        var et_content = findViewById<TextView>(R.id.et_content)
        var tv_btn_left = findViewById<TextView>(R.id.tv_btn_left)
        var tv_btn_right = findViewById<TextView>(R.id.tv_btn_right)
        var tv_tip_one = findViewById<TextView>(R.id.tv_tip_one)
        var tv_tip_two = findViewById<TextView>(R.id.tv_tip_two)

        tv_title.text = mTitle
//        tv_content.text = mContent
        tv_btn_left.text = mLeftText
        tv_btn_right.text = mRightText
        tv_tip_one.text = mTipOne
        tv_tip_two.text = mTipTwo

        tv_btn_left.setTextColor(mLeftBtnColor)
        tv_btn_right.setTextColor(mRightBtnColor)

        tv_btn_left.setOnClickListener {
            mLeftCancelBlock(it, this, et_content.text.toString())
        }

        tv_btn_right.setOnClickListener {
            mRightCancelBlock(it, this, et_content.text.toString())
        }


    }
}