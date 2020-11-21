package com.longpc.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.safmvvm.app.GlobalConfig
import com.safmvvm.utils.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv: TextView = findViewById(R.id.tv)

        tv.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var l: Long = currentTimeMills
                var time: String = l.format2DateString(DefaultDateFormat.DATE_YMD)
                LogUtil.e("现在是：" + time)
                ToastUtil.showShortToast("现在是：" + time)
            }
        })
    }
}