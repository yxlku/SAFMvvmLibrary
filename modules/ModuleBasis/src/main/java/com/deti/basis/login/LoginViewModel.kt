package com.deti.basis.login

import android.app.Application
import android.view.View
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath

class LoginViewModel(app: Application): BaseViewModel<LoginModel>(app){


    fun btToMainA(v: View){
        startActivityRouter(RouterActivityPath.TestApplication.PAGE_MAIN)
    }

}