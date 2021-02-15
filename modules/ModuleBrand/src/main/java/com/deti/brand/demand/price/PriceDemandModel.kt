package com.deti.brand.demand.price

import com.deti.brand.BrandApiService
import com.safmvvm.mvvm.model.BaseModel

class PriceDemandModel: BaseModel(){
    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)



}