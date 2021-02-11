package com.deti.brand.demand.create.item.placeorder

import androidx.databinding.ObservableField
import java.io.Serializable

data class ItemPlaceOrderEntity(
    var contentText: ObservableField<String> = ObservableField<String>("确认下单")
): Serializable