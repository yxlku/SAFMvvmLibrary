package com.deti.basis.register

import android.app.Application
import android.view.View
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class RegisterViewModel(app: Application): BaseViewModel<RegisterModel>(app) {

    fun clickClose(v: View){
        finish()
    }
}