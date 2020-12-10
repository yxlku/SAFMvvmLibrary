package com.longpc.testroom

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ToastUtil
import com.safmvvm.utils.jetpack.SingleLiveEvent
import com.safmvvm.utils.jetpack.putValue
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import java.lang.StringBuilder

/**
 * 测试数据库
 */
class TestRoomViewModel(app: Application): BaseViewModel<TestRoomModel>(app) {

    var testData: SingleLiveEvent<String> = SingleLiveEvent()
    /**
     * 添加 -- 没有返回值监听
     */
    fun btAdd(v: View){
        launchRequest {
            mModel.insertData()
        }
    }
    /**
     * 添加 -- 没有返回值监听
     */
    fun btAddFlow(v: View){
        launchRequest {
            mModel.insertDataFlow()
                .catch {
                    ToastUtil.showShortToast("插入失败！原因：" + it.message)
                }
                .collect {
                    ToastUtil.showShortToast("插入成功！")
                    btGetTableEntity(v)
                }
        }
    }

    fun btGetTableEntity(v: View){
        launchRequest {
            mModel.getAllData()
                .catch {
                    LogUtil.d("异常")
                }
                .collect {
                    var sb: StringBuilder = StringBuilder()
                    sb.append("caracas")
                    it?.forEach {
                        LogUtil.d(it.name+"xxxxx")
                        sb.append("id: ")
                        sb.append(it.id)
                        sb.append(" name: ")
                        sb.append(it.name)
                        sb.append(" age: ")
                        sb.append(it.age)
                        sb.append(" sex: ")
                        sb.append(it.sex)
                        sb.append("\n")
                    }
                    testData.putValue(sb.toString()+"ssss")
                    //滑到最底部
                    LiveDataBus.send("scd", Unit)
                }

        }
    }

}