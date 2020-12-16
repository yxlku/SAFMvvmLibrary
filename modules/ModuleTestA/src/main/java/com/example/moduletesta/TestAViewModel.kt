package com.example.moduletesta

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.safmvvm.component.RouterUtil
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.jetpack.SingleLiveEvent
import com.safmvvm.utils.jetpack.putValue
import com.test.common.RouterActivityPath

class TestAViewModel(app: Application): BaseViewModel<TestAModel>(app){
    var text = SingleLiveEvent<String>()

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    fun toTestAppliactionApp(v: View){
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//        ARouter.getInstance().build(RouterActivityPath.TestApplication.PAGE_MAIN).navigation();
//        RouterUtil.startActivity(RouterActivityPath.TestApplication.PAGE_MAIN){
//            return@startActivity it.withString("routerUtils", "我传值过来了")
//        }
//        startActivityRouterPostcard(RouterActivityPath.TestApplication.PAGE_MAIN){
//            it.withString("routerUtils", "我传值过来了2")
////                .greenChannel()
////                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        }
        //Router带结果跳转 - 可自定义添加Router函数
//        startActivityForResultRouterPostcard(RouterActivityPath.TestApplication.PAGE_MAIN, 1800){
//            it.withString("routerUtils", "我传值过来了2")
//                .withString("test", "我成功了！！！")
////                .greenChannel()
////                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        }
        //Router带结果跳转
//        startActivityForResultRouter(RouterActivityPath.TestApplication.PAGE_MAIN, 1800)
        //Router普通跳转
//        startActivityRouter(RouterActivityPath.TestApplication.PAGE_MAIN)
        //Router普通跳转-可自定义添加ARouter函数
        startActivityRouterPostcard(RouterActivityPath.TestApplication.PAGE_MAIN){
            return@startActivityRouterPostcard it.withString("routerUtils", "我传值过来了2")
                .withString("test", "2222我成功了！！！")
        }
    }

    override fun onActivityResultOk(intent: Intent) {
        var s = intent?.getStringExtra("ca")
        text.putValue(s)
    }
}