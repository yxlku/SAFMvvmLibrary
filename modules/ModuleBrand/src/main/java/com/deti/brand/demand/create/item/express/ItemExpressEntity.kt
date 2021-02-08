package com.deti.brand.demand.create.item.express

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.deti.brand.demand.create.item.IItemIsShow

/**
 * 快递类型
 */
data class ItemExpressEntity(
    var id: String = "",
    /** 快递单号*/
    var mExpressNum: ObservableField<String> = ObservableField<String>("")
): IItemIsShow()