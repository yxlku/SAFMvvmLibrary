package com.deti.basis.splash

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import com.deti.basis.login.LoginActivity
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.coroutines.flowCountDownDeal

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