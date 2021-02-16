package com.deti.brand.demand.progress.logistics

import com.deti.brand.BrandApiService
import com.safmvvm.mvvm.model.BaseModel

class LogisticsModel: BaseModel(){
    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)


}