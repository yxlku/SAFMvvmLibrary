package com.deti.brand.demand.create.entity

import com.test.common.entity.CommonDataDictionaryEntity
import java.io.Serializable

/**
 * 快递列表实体类
 */
data class DemandExpressListEntity(
    /** 快递列表*/
    var dataDictionaryList: ArrayList<CommonDataDictionaryEntity> = arrayListOf()
): Serializable
