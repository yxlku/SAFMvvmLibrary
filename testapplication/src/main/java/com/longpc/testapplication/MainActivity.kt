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


        var map = mutableMapOf<String, String>("1" to "a", "2" to "b", "3" to "c")
        var mapJson = JsonUtil.toJson(map)
        tv.setText(mapJson)
        LogUtil.json(mapJson)

        var jsonToMap: MutableMap<String, Any>? = JsonUtil.getJsonParseMapResult(mapJson) as MutableMap<String, Any>?
        var sb: StringBuilder = StringBuilder()
        sb.append("map转Json==============")
        sb.append("\n")
        sb.append(mapJson)
        sb.append("\n")
        sb.append("json转行为map=============")
        sb.append("\n")
        jsonToMap?.forEach {
            sb.append("key : " + it.key + " , value : " + it.value + "\n")
        }
        LogUtil.e(sb.toString())
        tv.setText(sb.toString())

        sb.append("\n")
        sb.append("json转行为map=============获取2对应的值")
        sb.append("\n")
        sb.append(jsonToMap?.get("2"))
        LogUtil.e(sb.toString())
        tv.setText(sb.toString())

        tv.setOnClickListener {
            var a: String? = null
            if(TextUtils.isEmpty(a)){
                LogUtil.e("java  的是空的")
            }
            a?.let {
                LogUtil.e("kotlin  的是空的")
            }
        }
    }
}