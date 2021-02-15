package com.test.common.ui.popup.comfirm

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.core.CenterPopupView
import com.test.common.R
import com.test.common.ui.popup.comfirm.single.SingleAdapter
import com.test.common.ui.popup.comfirm.single.SingleEntity

/**
 * 确定取消弹窗
 */
class ComfirmAndCancelRemarkSinglePopupView(
    var mActivity: Activity,
    var mTitle: String? = "温馨提示",
    var mTipOne: String? = "",
    var mTipTwo: String? = "",
    /** 单选数据*/
    var singleEntitys: ArrayList<SingleEntity> = arrayListOf(),
    /** 文本框hint*/
    var mRemarkHint: String = "",
    var mLeftText: String? = "取消",
    var mRightText: String? = "确定",
    /** 左侧文字颜色*/
    var mLeftBtnColor: Int = Color.parseColor("#333333"),
    /** 右侧文字颜色*/
    var mRightBtnColor: Int = Color.parseColor("#1A97FF"),
    var mLeftCancelBlock: (view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity) -> Unit = { view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity -> },
    var mRightCancelBlock: (view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity) -> Unit = { view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity -> },
) : CenterPopupView(mActivity) {

    override fun getImplLayoutId(): Int = R.layout.base_dialog_comfirm_and_cancel_remark_single



    override fun onCreate() {
        super.onCreate()

        var tv_title = findViewById<TextView>(R.id.tv_title)
        var et_content = findViewById<EditText>(R.id.et_content)
        var tv_btn_left = findViewById<TextView>(R.id.tv_btn_left)
        var tv_btn_right = findViewById<TextView>(R.id.tv_btn_right)
        var tv_tip_one = findViewById<TextView>(R.id.tv_tip_one)
        var tv_tip_two = findViewById<TextView>(R.id.tv_tip_two)
        var rv_content = findViewById<RecyclerView>(R.id.rv_content)

        et_content.hint = mRemarkHint

        tv_title.text = mTitle
//        tv_content.text = mContent
        tv_btn_left.text = mLeftText
        tv_btn_right.text = mRightText
        tv_tip_one.text = mTipOne
        tv_tip_two.text = mTipTwo

        tv_btn_left.setTextColor(mLeftBtnColor)
        tv_btn_right.setTextColor(mRightBtnColor)
        //选中的数据
        var sEntity = SingleEntity()
        var mAdapter = SingleAdapter{view: View, adapter: SingleAdapter, position: Int, selectEntity: SingleEntity ->
            sEntity = selectEntity
        }
        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(singleEntitys)

        tv_btn_left.setOnClickListener {
            mLeftCancelBlock(it, this, et_content.text.toString(), sEntity)
        }

        tv_btn_right.setOnClickListener {
            mRightCancelBlock(it, this, et_content.text.toString(), sEntity)
        }


    }
}