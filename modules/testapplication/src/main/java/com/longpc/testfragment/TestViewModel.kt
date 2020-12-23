package com.longpc.testfragment

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.longpc.testapplication.MainActivity
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil

class TestViewModel(app: Application): BaseViewModel<TestModel>(app){

    var text = SingleLiveEvent<String>()
    var text2 = SingleLiveEvent<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LiveDataBus.observe<Intent>(this, "finishResult", Observer {
            LogUtil.d("ssssssssssssssssssssssss")
            var s = it.getStringExtra("ca")
            text.putValue("dddd${s}")
        }, false)
//        LiveDataBus.observe<Pair<Int?, Intent?>>(
//            this,
//            "mainViewModel",
//            Observer {
//                var s = it.second?.getStringExtra("ca")
//                text.putValue("dddd${s}")
//            },
//            true
//        )
        LiveDataBus.observe<Pair<Int?, Intent?>>(
            this,
            "mainViewModel2",
            Observer {
                var s = it.second?.getStringExtra("ca")
                text2.putValue("dddd${s}")
            },
            true
        )
        onPageResult("mainViewModel"){resultCode, intent ->
            var s = intent?.getStringExtra("ca")
            text.putValue("11111dddd${s}")
        }

    }

    fun btToRouter(v: View){
//        startActivityRouter(RouterActivityPath.TestApplication.PAGE_MAIN)
        startActivity(MainActivity::class.java)
//        startActivityForResultRouter(RouterActivityPath.TestApplication.PAGE_MAIN, 0)
    }



}