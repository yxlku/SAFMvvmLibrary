package com.deti.brand.demand.price.pricelist.adapter

import android.view.View
import com.safmvvm.app.BaseApp
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.coroutines.flowCountDownDeal
import com.test.common.ui.toast.ToastDrawableEnum

class PriceListItemViewModel: BaseViewModel<BaseModel>(BaseApp.getInstance()) {

    fun clickTip(view: View){
        ToastUtil.showShortToast("需求提交成功！我们会在\n24小时内报价！", true, ToastDrawableEnum.TOP)
    }

//    fun cuntTime(){
//        launchUI {
//            flowCountDownDeal(
//                initTime,
//                dealBeforeBlock = {
//                    timeText.putValue(initTime)
//                    LogUtil.d("倒计时开始")
//                },
//                dealingBlock = {
//                    timeText.putValue(it)
//                    LogUtil.d("倒计时：${it}")
//                },
//                dealFinishedBlock = {
//                    LogUtil.d("倒计时结束")
//                    startActivity(LoginActivity::class.java)
////                    startActivityRouter(RouterActivityPath.ModuleTestA.PAGE_TESTA)
//                    finish()
//                }
//            )
//        }
//    }
}