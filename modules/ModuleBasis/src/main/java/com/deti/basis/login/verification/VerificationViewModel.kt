package com.deti.basis.login.verification

import android.app.Application
import android.view.View
import com.deti.basis.register.RegisterAcitivty
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath

class VerificationViewModel(app: Application): BaseViewModel<VerificationModel>(app){


    /**
     * 跳转到注册页面
     */
    fun clickToPageRegister(v: View){
        startActivity(RegisterAcitivty::class.java)
    }


    fun clickToPageIndex(v: View){
        startActivityRouter(RouterActivityPath.ModuleBrand.PAGE_MAIN)
    }

}