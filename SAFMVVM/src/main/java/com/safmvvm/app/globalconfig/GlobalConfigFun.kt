package com.safmvvm.app.globalconfig

import com.zy.multistatepage.MultiStateConfig
import com.zy.multistatepage.MultiStatePage


/**
 * 通过方法配置 全局设置
 */
internal object GlobalConfigFun {

    /**
     * 初始化页面状态布局
     */
    fun initPageStateConfig(
        /** 动画持续时间 默认500毫秒*/
        alphaDuration: Long = GlobalConfig.Loading.LOADING_ALPHA_DURATION,
    ) {
        //基础配置
        val config = MultiStateConfig.Builder()
            .alphaDuration(alphaDuration)
            .build()
        MultiStatePage.config(config)
    }

//    /**
//     * 初始化版本更新功能 - 除了检查是否需要更新
//     */
//    fun initUpdateApk(){
//        XUpdate.get()
//            .debug(GlobalConfig.Log.gIsOpenLog)
//            .isWifiOnly(false) //默认设置只在wifi下检查版本更新
//            .isGet(true) //默认设置使用get请求检查版本
//            .isAutoMode(false) //默认设置非自动模式，可根据具体使用配置
//            .setOnUpdateFailureListener { error ->
//                //设置版本更新出错的监听
//                if (error.code != CHECK_NO_NEW_VERSION) {          //对不同错误进行处理
//                    ToastUtil.showShortToast(error.toString())
//                }
//            }
//            .supportSilentInstall(true) //设置是否支持静默安装，默认是true
//            .setIUpdateHttpService(OKHttpUpdateHttpService()) //这个必须设置！实现网络请求功能。
//            .init(BaseApp)
//    }
}