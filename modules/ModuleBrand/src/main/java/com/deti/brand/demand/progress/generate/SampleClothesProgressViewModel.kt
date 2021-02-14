package com.deti.brand.demand.progress.generate

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel

class SampleClothesProgressViewModel(app: Application): BaseViewModel<SampleClothesProgressModel>(app){

    /**
     * 更新基础信息
     * @param 状态
     */
    val PROGRESS_UPDATE_UI_STATE = SingleLiveEvent<Int>()
    /** 快递名称*/
    var mExPressName: ObservableField<String> = ObservableField("")
    /** 快递编号*/
    var mExPressCode: ObservableField<String> = ObservableField("")

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        var demandId = getArgumentsIntent()?.getStringExtra("demandId")
        if (!demandId.isNullOrEmpty()) {
            //TODO 都痛了再删除这里
            demandId = "lixtext1"
            requestFindExpressInfo(demandId)
        }
    }

    /**
     * 获取进度信息
     */
    fun requestFindExpressInfo(demandId: String){
        launchRequest {
            mModel.requestFindExpressInfo(demandId).flowDataDeal(
                loadingModel = LoadingModel.LOADING,
                onSuccess = {
                    it?.data?.apply {
                        //0、样衣状态
                        PROGRESS_UPDATE_UI_STATE.putValue(status.toInt() - 1)
                        //1、快递名称
                        mExPressName.set(expressName)
                        //2、快递单号
                        mExPressCode.set(expressCode)
                        //3、快递进度
                    }
                }
            )
        }
    }

}