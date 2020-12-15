package com.example.moduletesta

import android.app.Application
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath

class TestAViewModel(app: Application): BaseViewModel<TestAModel>(app){


    fun toTestAppliactionApp(v: View){
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
        ARouter.getInstance().build(RouterActivityPath.TestApplication.PAGE_MAIN).navigation();
    }
}