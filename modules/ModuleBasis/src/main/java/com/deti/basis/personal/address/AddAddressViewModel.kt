package com.deti.basis.personal.address

import android.app.Application
import android.view.View
import com.safmvvm.app.BaseApp
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class AddAddressViewModel(app: Application): BaseViewModel<AddAddressModel>(app){

    fun clickSubmit(view: View){
        LiveDataBus.send("submit", Unit)
    }
}