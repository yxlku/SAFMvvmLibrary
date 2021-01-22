package com.test.common.ui.popup.time

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.DateTimePicker
import com.loper7.date_time_picker.StringUtils
import com.lxj.xpopup.core.BottomPopupView
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.utils.currentTimeMills
import com.test.common.R
import java.util.*

/**
 * 时间选择
 */
class DatePopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var block: (millisecond: Long, time: String) -> Unit = {millisecond: Long, time: String->}
) : BottomPopupView(mActivit) {
    private var mMillisecond: Long = 0
    override fun getImplLayoutId(): Int = R.layout.base_dialog_date

    override fun onCreate() {
        super.onCreate()

        var tb_title: TitleBar = findViewById(R.id.tb_title)
        var dtp_content: DateTimePicker = findViewById(R.id.dtp_content)
        tb_title.title = mTitle

        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
                dismiss()
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                block(
                    mMillisecond,
                    StringUtils.conversionTime(mMillisecond, "yyyy 年 MM 月 dd 日 ")
                )
                dismiss()
            }

        })

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


}