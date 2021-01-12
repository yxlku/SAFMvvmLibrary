package com.deti.brand.demand.create.item.service

import com.safmvvm.bus.SingleLiveEvent

data class ItemServiceEntity(
    /** 选中服务类型item的Id*/
    var selectedTypeId: String = "",
    /** 选中服务类型item的文字*/
    var selectedTypeText: String= "",

    /** 对应服务Id*/
    var seletedServiceId: String = "",
    /** 对应服务文字*/
    var seletedServiceText: String = "",

)