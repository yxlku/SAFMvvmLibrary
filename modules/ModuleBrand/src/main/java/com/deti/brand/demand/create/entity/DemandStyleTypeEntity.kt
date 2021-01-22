package com.deti.brand.demand.create.entity

import java.io.Serializable

/**
 * 创建需求：款式分类
 */
data class DemandStyleTypeEntity(
    var tree: List<DemandStyleEntity>? = null,
): Serializable

data class DemandStyleEntity(
    var name: String = "",
    var children: List<DemandStyleEntity>? = null,
    var code: String = "",
    var id: String = "",
): Serializable