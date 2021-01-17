package com.deti.brand.demand.progress.logistics.adapter2

import androidx.annotation.DrawableRes
import com.deti.brand.demand.progress.OrderStatus
import java.io.Serializable

/**
 * 物流状态实体类
 */
data class LogisticsStateEntity(
    var message: String,
    var status: OrderStatus,
    @DrawableRes var iconCheck: Int,
    @DrawableRes var iconUnCheck: Int,
): Serializable