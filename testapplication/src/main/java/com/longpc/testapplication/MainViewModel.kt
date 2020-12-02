package com.longpc.testapplication

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.safmvvm.http.result.ResponseResultCallback
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.StringBuilder

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(app: Application): BaseViewModel<MainModel>(app) {
    var text = ObservableField<String>()

    override fun onResume(owner: LifecycleOwner) {
        testRequestFlow()
    }

     fun test(){
        launchNet({
            mModel.testMainNet()
        }, object : ResponseResultCallback<MainDataEntity>{
            override fun onLoading(lodingText: String) {
            }

            override fun onSuccess(data: MainDataEntity?) {
                ToastUtil.showShortToast(data?.text+"sss")
                LogUtil.e(data?.text+"sss")
                var sb = StringBuilder()
                data?.datas?.forEach {
                    sb.append(it.title)
                    sb.append("\n")
                }
                text.set(sb.toString())
            }

            override fun onFailed(code: String, msg: String) {
                ToastUtil.showShortToast(msg)
                LogUtil.e(msg)
            }

            override fun onComplete() {
            }

        })
    }
//    inline fun <T> launchRequest(a:Flow<BaseNetEntity<MainDataEntity?>?){
    fun testFolw(a: Flow<*>){
        viewModelScope.launch {
            mModel.testMainNetFolw()
                .onStart {
                    LogUtil.d("我在用Flow请求了！")
                }
                .onCompletion {
                    LogUtil.d("我用Flow请求完事了！")
                }
                .collect {
                    var data = it?.data
                    ToastUtil.showShortToast(data?.text + "sss")
                    LogUtil.e(data?.text + "sss")
                    var sb = StringBuilder()
                    data?.datas?.forEach {
                        sb.append(it.title)
                        sb.append("\n")
                    }
                    text.set(sb.toString())
                }
//            .collectLatest {result ->
//
//
//            }
        }
    }
    fun testRequestFlow(){
        launchRequest {
            mModel.testMainNetFolw()
                .flowDataDeal(
                    loadingModel = LoadingModel.LOADSIR,
                    onError = {

                    },
                    onSuccess = {
                        it?.data?.text?.let { it1 -> LogUtil.e(it1 + "我擦，竟然可以了") }

                        var data = it?.data
                        ToastUtil.showShortToast(data?.text + "sss")
                        LogUtil.e(data?.text + "sss")
                        var sb = StringBuilder()
                        data?.datas?.forEach {
                            sb.append(it.title)
                            sb.append("\n")
                        }
                        text.set(sb.toString())
                    },
                    onFaile = {code, msg->

                    }
                )

        }
    }


    fun tvClick(v: View){
        if (v is TextView) {
//            ToastUtil.showShortToast("我是TextView，点击了：" + text.get())
//            var result = mModel.testMainNet("年轻人不讲武德，")
//            ToastUtil.showShortToast(result)
//            LogUtil.e(result)
            testRequestFlow()

//            testFolw()
        }else{
            ToastUtil.showShortToast("擦：" + text.get())
        }
    }
}