package com.deti.basis.login.verification

import android.app.Application
import android.view.View
import com.deti.basis.register.RegisterAcitivty
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class VerificationViewModel(app: Application): BaseViewModel<VerificationModel>(app){



    fun clickToPageRegister(v: View){
        startActivity(RegisterAcitivty::class.java)
    }
}