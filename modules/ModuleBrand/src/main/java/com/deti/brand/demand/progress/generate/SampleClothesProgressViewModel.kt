package com.deti.brand.demand.progress.generate

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.deti.brand.demand.progress.generate.entity.InfoListDataBean
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.ui.toast.ToastUtil

class SampleClothesProgressViewModel(app: Application): BaseViewModel<SampleClothesProgressModel>(app){

    /**
     * 更新基础信息
     * @param 状态
     */
    val PROGRESS_UPDATE_UI_STATE = SingleLiveEvent<Int>()
    /** 更新快递进度列表*/
    val PROGRESS_UPDATE_UI_LOGISTICS = SingleLiveEvent<ArrayList<InfoListDataBean>>()
    /** 快递名称*/
    var mExPressName: ObservableField<String> = ObservableField("")
    /** 快递编号*/
    var mExPressCode: ObservableField<String> = ObservableField("")

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        var demandId = getArgumentsIntent()?.getStringExtra("demandId")
        if (!demandId.isNullOrEmpty()) {
            //TODO 都痛了再删除这里
            demandId = "40288a8b7786002a01778aee89060072"
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
                        try {
                            PROGRESS_UPDATE_UI_STATE.putValue(status.toInt() - 1)
                        } catch (e: Exception) {
                            PROGRESS_UPDATE_UI_STATE.putValue( - 1)
                        }
                        //1、快递名称
                        mExPressName.set(expressName)
                        //2、快递单号
                        mExPressCode.set(expressCode)
                        //3、快递进度
                        PROGRESS_UPDATE_UI_LOGISTICS.putValue(infoList)
                    }
                },
                onFaile = {code: String, msg: String ->
                    ToastUtil.showShortToast(msg)
                    finish()
                }
            )
        }
    }

}