package com.longpc.testapplication

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.safmvvm.http.result.ResponseResultCallback
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.lang.StringBuilder

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(app: Application): BaseViewModel<MainModel>(app) {
    var text = ObservableField<String>()

    override fun onResume(owner: LifecycleOwner) {
    }

     fun test(){
        launch({
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

    suspend fun testFolw(){
        mModel.testMainNetFolw()
            .onStart {
                LogUtil.d("我在用Flow请求了！")
            }
            .onCompletion {
                LogUtil.d("我用Flow请求完事了！")
            }
            .collect {
                var data = it?.data
                ToastUtil.showShortToast(data?.text+"sss")
                LogUtil.e(data?.text+"sss")
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

    fun tvClick(v: View){
        if (v is TextView) {
//            ToastUtil.showShortToast("我是TextView，点击了：" + text.get())
//            var result = mModel.testMainNet("年轻人不讲武德，")
//            ToastUtil.showShortToast(result)
//            LogUtil.e(result)
            viewModelScope.launch{
                testFolw()
            }

//            testFolw()
        }else{
            ToastUtil.showShortToast("擦：" + text.get())
        }
    }
}