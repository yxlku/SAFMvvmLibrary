package com.deti.brand.demand.create.item.service

import androidx.databinding.ObservableField
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import java.io.Serializable

data class ItemServiceEntity(
    /** 服务类型*/
    var mServiceType: ObservableField<BaseSingleChoiceEntity> = ObservableField<BaseSingleChoiceEntity>(),
    /** 对应服务*/
    var mServiceProduce: ObservableField<BaseSingleChoiceEntity> = ObservableField<BaseSingleChoiceEntity>()
):Serializable