package com.safmvvm.file.update.dialog

/**
 * 版本更新弹窗接口，所有自定义更新接口都要实现此接口
 */
interface IUpdateProgressDialog {

    /**
     * 进度更新
     */
    fun progress(progress: Int)

    /**
     * 更新完成
     */
    fun success()

    /**
     * 更新终止，下载中发生错误
     */
    fun error()

}