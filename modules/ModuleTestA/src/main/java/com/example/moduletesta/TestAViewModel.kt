package com.example.moduletesta

import android.app.Application
import android.view.View
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath

class TestAViewModel(app: Application): BaseViewModel<TestAModel>(app){
    var text = SingleLiveEvent<String>()

    fun backFinish(){
    }

//    fun clickShowToastLeft(view: View){
////        ToastUtil.showShortToast("我擦牛皮我擦牛皮我擦牛皮我擦牛皮我擦牛皮擦牛皮我擦牛皮擦牛皮我擦牛皮擦牛皮我擦牛皮")
//        ToastUtil.showShortToast("显示左侧", toastEnumInterface = ToastDrawableEnum.FAIL)
//    }
//
//    fun clickShowToast(view: View){
//        ToastUtil.showShortToast("不显示", true)
//    }
//    fun clickShowToastRight(view: View){
////        ToastUtil.showShortToast("我擦牛皮我擦牛皮我擦牛皮我擦牛皮我擦牛皮擦牛皮我擦牛皮擦牛皮我擦牛皮擦牛皮我擦牛皮")
//        ToastUtil.showShortToast("显示右侧", true, ToastDrawableEnum.RIGHT)
//    }
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

    fun btnLogout(v: View){
        //清理用户信息内存
        //跳转到登录页面 -- 不用杀死当前页面
        startActivityRouter(RouterActivityPath.ModuleBasis.PAGE_LOGIN)
    }

}