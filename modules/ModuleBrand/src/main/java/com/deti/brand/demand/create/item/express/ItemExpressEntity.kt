package com.deti.brand.demand.create.item.express

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.deti.brand.demand.create.item.IItemIsShow
import com.test.common.ui.popup.base.BaseSingleChoiceEntity

/**
 * 快递类型
 */
data class ItemExpressEntity(
    var id: String = "",
    /** 快递单号*/
    var mExpressNum: ObservableField<String> = ObservableField<String>(""),

    /** 样衣 - 快递选择信息 -- 最后的数据信息 */
    var mExpressSingleChoiceEntity: ObservableField<BaseSingleChoiceEntity> = ObservableField<BaseSingleChoiceEntity>()
): IItemIsShow()