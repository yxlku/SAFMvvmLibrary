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

    /** 服务类型*/
    var mServiceType = ObservableField<String>()
    /** 对应服务*/
    var mServiceProduce = ObservableField<String>()

    /** 快递选择信息 -- 最后的数据信息 */
    var mExpressSingleChoiceEntity = ObservableField<BaseSingleChoiceEntity>()
    /** 快递单号*/
    var mExpressNum = ObservableField<String>()
    /** 快递单号输入监听*/
    var consumerExpressNum = BindingConsumer<String> { t -> mExpressNum.set(t) }

    /**
     * 服务类型
     */
    fun clickServiceType(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("0", "包工包料"),
            BaseSingleChoiceEntity("1", "纯加工"),
        )
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_TYPE, datas)
    }
    /**
     * 对应服务
     */
    fun clickServiceProduce(view: View){
        var datas = arrayListOf(
            BaseSingleChoiceEntity("0", "打版 + 生产"),
            BaseSingleChoiceEntity("1", "仅生产"),
        )
        var pos = datas.indexOf(mServiceProduce.get())
        LiveDataBus.send(CreateDemandFragment.DIALOG_SERVICE_PRODUCE, datas)
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