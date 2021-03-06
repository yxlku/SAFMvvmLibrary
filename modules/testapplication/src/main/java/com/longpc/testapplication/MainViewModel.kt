package com.longpc.testapplication

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.longpc.testfragment.TestFragmentActivity
import com.longpc.testroom.TestRoomActivity
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.http.result.ResponseResultCallback
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.ui.toast.ToastUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.StringBuilder

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(app: Application): BaseViewModel<MainModel>(app) {
    var text = ObservableField<String>()

    override fun onResume(owner: LifecycleOwner) {
//        testRequestFlow()
        //ARouter，除了使用注解@Autowired，也可以使用普通传参方式
//        text.set(getArgumentsIntent()?.getStringExtra("routerUtil"))
//        text.set(routerUtils)
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
            mModel.testInLineFolw()
                .flowDataDeal(
                    loadingModel = LoadingModel.LOAD_PAGESATE,
                    onError = {

                    },
                    onSuccess = {
                        var data = it?.data
//                        ToastUtil.showShortToast(data?.text + "sss")
                        LogUtil.e(data?.text + "sss")
                        var sb = StringBuilder()
                        data?.datas?.forEach {
                            sb.append(it.title)
                            sb.append("\n")
                        }
//                        text.set(sb.toString())
                        text.set(data?.text + "sss")
                    },
                    onFaile = {code, msg->

                    }
                )

        }
    }

    fun testPostFlow(){
        launchRequest {
            mModel.testPostFlow()
                .flowDataDeal(
                    loadingModel = LoadingModel.LOAD_PAGESATE,
                    onSuccess = {
                        var data = it?.result
//                        ToastUtil.showShortToast(data?.text + "sss")
//                        LogUtil.e(data?.title + "sss")
                        var sb = StringBuilder()
                        data?.forEach {
                            sb.append(it?.title)
                            sb.append("\n")
                        }
//                        text.set(sb.toString())
                        text.set(it?.message)
                    },
                    onFaile = {code, msg ->

                    },
                    onError = {

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
//            testPostFlow()
//            testFolw()
        }else{
            ToastUtil.showShortToast("擦：" + text.get())
        }
    }

    fun roomClick(v: View){
//        startActivityForResult(TestRoomActivity::class.java)
        startActivity(TestRoomActivity::class.java)
    }

    fun timeClick(v: View){
        LiveDataBus.send("timeDialog", Unit)
    }

    fun updateVersion(v: View){
        LiveDataBus.send("updateVersion", Unit)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        onPageResult("testRoom"){resultCode, intent ->
            intent?.run {
                var name = intent.getStringExtra("name")
                var age = intent.getIntExtra("age", 20)
                text.set("name: ${name}, age: $age")
                LogUtil.d("name: ${name}, age: $age")
            }
        }
    }
    fun btFinish(v: View){
        var intent = Intent()
        intent.putExtra("ca", "我是返回值")
//        LiveDataBus.send("finishResult", intent)
        resultFinish("mainViewModel", Activity.RESULT_OK, intent)
    }
    fun btFinish2(v: View){
        var intent = Intent()
        intent.putExtra("ca", "我是返回值222222222222")
//        LiveDataBus.send("finishResult", intent)
        resultFinish("mainViewModel2", Activity.RESULT_OK, intent)
    }
    fun btToFragment(v: View){
        startActivity(TestFragmentActivity::class.java)
    }
}