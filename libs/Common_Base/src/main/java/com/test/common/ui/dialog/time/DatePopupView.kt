package com.test.common.ui.dialog.time

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.DateTimePicker
import com.loper7.date_time_picker.StringUtils
import com.loper7.date_time_picker.dialog.CardDatePickerDialog
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopupext.popup.TimePickerPopup
import com.safmvvm.utils.currentTimeMills
import com.test.common.R
import com.test.common.ui.dialog.tip.createDialogTip
import me.jessyan.autosize.utils.AutoSizeUtils
import java.util.*

class DatePopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var block: (millisecond: Long, time: String) -> Unit = {millisecond: Long, time: String->}
) : BottomPopupView(mActivit), View.OnClickListener {
    private var mMillisecond: Long = 0
    override fun getImplLayoutId(): Int = R.layout.base_dialog_date

    override fun onCreate() {
        super.onCreate()
        var tv_title: TextView = findViewById(R.id.tv_title)
        var tv_sure: TextView = findViewById(R.id.tv_sure)
        var iv_close: ImageFilterView = findViewById(R.id.iv_close)
        var dtp_content: DateTimePicker = findViewById(R.id.dtp_content)

        tv_title.text = mTitle
        iv_close.setOnClickListener(this)
        tv_sure.setOnClickListener(this)
        dtp_content.apply {
            //格式
            setDisplayType(intArrayOf(
                DateTimeConfig.YEAR,//显示年
                DateTimeConfig.MONTH,//显示月
                DateTimeConfig.DAY,//显示日
            ))
            //默认选中时间
            setDefaultMillisecond(currentTimeMills)//defaultMillisecond 为毫秒时间戳
            //最小时间
            setMinMillisecond(currentTimeMills)//defaultMillisecond 为毫秒时间戳
            //不允许循环
            setWrapSelectorWheel(false)
            setOnDateTimeChangedListener{millisecond ->
                this@DatePopupView.mMillisecond = millisecond
            }
        }
    }



    override fun onClick(v: View) {
        when (v.id) {
            //关闭
            R.id.iv_close -> dismiss()
            //确定 -- 选中
            R.id.tv_sure -> {
                block(
                    mMillisecond,
                    StringUtils.conversionTime(mMillisecond, "yyyy 年 MM 月 dd 日 ")
                )
                dismiss()
            }
        }
    }
}