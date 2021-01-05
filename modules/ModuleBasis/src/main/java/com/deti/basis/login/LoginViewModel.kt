package com.deti.basis.login

import android.app.Application
import android.view.View
import com.deti.basis.register.RegisterAcitivty
import com.safmvvm.app.AppActivityManager
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.weight.TextViewDrawableEnum
import com.test.common.RouterActivityPath
import com.test.common.ui.ToastDrawableEnum

class LoginViewModel(app: Application): BaseViewModel<LoginModel>(app){


    fun btToMainA(v: View){
        if(AppActivityManager.countActivity() <= 1){
            //如果Activity栈中只有登录页面时，创建个新主页，如果有其他页面则关闭登录即可，可以使登录后继续操作之前的页面功能
            startActivityRouter(RouterActivityPath.ModuleTestA.PAGE_TESTA)
        }
        finish()
    }


}