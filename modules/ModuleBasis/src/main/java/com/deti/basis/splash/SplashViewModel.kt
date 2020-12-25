package com.deti.basis.splash

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class SplashViewModel(app: Application): BaseViewModel<SplashModel>(app) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        
    }

}