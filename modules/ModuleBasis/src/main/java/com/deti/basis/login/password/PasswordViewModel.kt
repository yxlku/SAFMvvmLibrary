package com.deti.basis.login.password

import android.app.Application
import android.view.View
import com.deti.basis.register.RegisterAcitivty
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath

class PasswordViewModel(app: Application): BaseViewModel<PasswordModel>(app){

    fun clickToPageRegister(v: View){
        startActivity(RegisterAcitivty::class.java)
    }
    fun clickToLogin(v: View){
        startActivityRouter(RouterActivityPath.ModuleBasis.PAGE_SEARCH)
    }
}