package com.test.common.ui.dialog.types

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

class TypesPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
) : BottomPopupView(mActivit), View.OnClickListener {
    override fun getImplLayoutId(): Int = R.layout.base_dialog_types

    override fun onCreate() {
        super.onCreate()
        var tv_title: TextView = findViewById(R.id.tv_title)
        var tv_sure: TextView = findViewById(R.id.tv_sure)
        var iv_close: ImageFilterView = findViewById(R.id.iv_close)

        tv_title.text = mTitle
        iv_close.setOnClickListener(this)
        tv_sure.setOnClickListener(this)
    }



    override fun onClick(v: View) {
        when (v.id) {
            //关闭
            R.id.iv_close -> dismiss()
        }
    }
}