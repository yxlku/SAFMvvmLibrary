package com.deti.designer.order.popup

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import com.deti.designer.R
import com.deti.designer.order.OrderGrabFragment
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView

/**
 * 任务提醒
 */
class TaskReminderPopupView(
    var mActivity: Activity,
    var mState: Int = OrderGrabFragment.STATE_GRAB
) : BottomPopupView(mActivity) {

    override fun getImplLayoutId(): Int = R.layout.designer_popup_tast_reminder

    var tv_cancel: TextView? = null
    var tv_sure: TextView? = null

    override fun onCreate() {
        super.onCreate()
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_sure = findViewById(R.id.tv_sure)
        var iv_close = findViewById<ImageView>(R.id.iv_close)

        controlBtns()

        tv_cancel?.setOnClickListener{
            dismiss()
        }
        iv_close?.setOnClickListener{
            dismiss()
        }
    }

    private fun controlBtns() {
        tv_sure?.apply {
            if (mState == OrderGrabFragment.STATE_GRAB) {
                //抢单
                text = "确认抢单"
            }else{
                //接单
                text = "确认接单"
            }
        }
    }
}