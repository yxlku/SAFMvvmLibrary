package com.deti.brand.demand.create

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableField
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_EXPRESS_LIST
import com.deti.brand.demand.create.CreateDemandFragment.Companion.DIALOG_TIP_ADDRESS
import com.safmvvm.binding.command.BindingConsumer
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
class CreateDemandViewModel(app: Application) : BaseViewModel<CreateDemandModel>(app) {
    /** 快递选择信息 -- 最后的数据信息 */
//    var mExpressSingleChoiceEntity = SingleLiveEvent<BaseSingleChoiceEntity>()
    var mExpressSingleChoiceEntity = ObservableField<BaseSingleChoiceEntity>()
    var mExpressNum = ObservableField<String>()
    var c = object : BindingConsumer<String>{
        override fun call(t: String?) {
            mExpressNum.set(t)
        }
    }

    /**
     * 地址弹窗
     */
    fun clickAddress(view: View){
        LiveDataBus.send(DIALOG_TIP_ADDRESS, Pair(view,"浙江省杭州市余杭区临平区余杭商会大厦C座 联系电话：123 4567 8910"))
    }
    /**
     * 获取快递列表
     */
    fun clickRequestExpressList(view: View) {
        LogUtil.d("快递单号：${mExpressNum.get()}")
        launchRequest {
            mModel.requestExpressList()
                .flowDataDeal(
                    loadingModel = LoadingModel.NULL,
                    onSuccess = {
                        var baseSingleChoiceEntitys = arrayListOf<BaseSingleChoiceEntity>()
                        it?.data?.dataDictionaryList?.forEach { dataDictionaryEntity ->
                            baseSingleChoiceEntitys.add(
                                BaseSingleChoiceEntity(
                                    dataDictionaryEntity.id,
                                    dataDictionaryEntity.text
                                )
                            )
                        }
                        if(baseSingleChoiceEntitys.size > 0){
                            var pos = baseSingleChoiceEntitys.indexOf(mExpressSingleChoiceEntity.get())
                            LiveDataBus.send(DIALOG_EXPRESS_LIST, Pair(baseSingleChoiceEntitys, pos))
                        }
                    }
                )
        }
    }

    /**
     * 请求：获取颜色列表
     */
    fun requestColors() {

    }

    /**
     * 请求：获取款式列表
     */
    fun requestStyles() {

    }

}