package com.deti.basis.splash

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LifecycleOwner
import com.deti.basis.login.LoginActivity
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.DefaultDateFormat
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.coroutines.flowCountDownDeal
import com.safmvvm.utils.coroutines.flowCountdown
import com.safmvvm.utils.format2DateString
import com.safmvvm.utils.formatAgoStyleForWeibo
import com.test.common.RouterActivityPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class SplashViewModel(app: Application) : BaseViewModel<SplashModel>(app) {
    private val initTime: Long = 0
    var timeText = SingleLiveEvent<Long>()
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //倒计时
        launchUI {
            flowCountDownDeal(
                initTime,
                dealBeforeBlock = {
                    timeText.putValue(initTime)
                    LogUtil.d("倒计时开始")
                },
                dealingBlock = {
                    timeText.putValue(it)
                    LogUtil.d("倒计时：${it}")
                },
                dealFinishedBlock = {
                    LogUtil.d("倒计时结束")
                    startActivity(LoginActivity::class.java)
//                    startActivityRouter(RouterActivityPath.ModuleTestA.PAGE_TESTA)
                    finish()
                }
            )
        }
    }
}