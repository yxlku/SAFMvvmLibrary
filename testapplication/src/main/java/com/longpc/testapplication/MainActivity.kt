package com.longpc.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.safmvvm.app.GlobalConfig
import com.safmvvm.utils.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.tv)

        tv.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var a: String? = null
                if(TextUtils.isEmpty(a)){
                    LogUtil.e("java  的是空的")
                }
                a?.let {
                    LogUtil.e("kotlin  的是空的")
                }
            }
        })
    }
}