package com.safmvvm.http

import android.os.Parcelable
import com.orhanobut.logger.Logger
import com.safmvvm.R
import com.safmvvm.app.GlobalConfig
import com.safmvvm.http.entity.IBaseResponse
import com.safmvvm.http.result.ResponseResultCallback
import com.safmvvm.http.result.state.entityNullable
import com.safmvvm.http.result.state.notHttpException
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ResUtil
import retrofit2.HttpException
import java.io.Serializable
import java.lang.Exception
import java.lang.RuntimeException

/**
 * http请求处理
 */
object HttpDeal {

    /**
     * 请求结果处理
     * @param entity 返回实体类
     * @param listener 请求状态监听 可以为空，为空则不做任何处理，只是单纯的请求
     */
    fun <T: Serializable> dealResult(
        entity: IBaseResponse<T?>?,
        listener: ResponseResultCallback<T>?
    ){
        //请求需要配置，成功码，否则不能返回结果
        if (GlobalConfig.Request.SUCCESS_CODE.isEmpty()) {
            throw RuntimeException(ResUtil.getString(R.string.net_not_init_success_code))
            return
        }
        listener?.let {
            //请求统一处理
            if (entity == null) {
                //提示实体为空
                listener.onFailed(entityNullable.toString(), ResUtil.getString(R.string.net_msg_entity_nullable))
                return
            }
            val code = entity.code()
            val msg = entity.msg()
            val data: T? = entity.data()
            Logger.d(data)
            if (code.isEmpty()) {
                listener.onFailed(entityNullable.toString(), ResUtil.getString(R.string.net_msg_entity_code_nullable))
                return
            }
            //结果正常处理
            if (code == GlobalConfig.Request.SUCCESS_CODE) {
                //请求成功，返回成功
                listener.onSuccess(data)
            }else{
                //请求成功但服务器返回错误
                listener.onFailed(entityNullable.toString(), msg)
            }
        }
    }

    /**
     * 处理异常信息
     */
    fun <T: Serializable> dealException(
        ex: Exception,      //异常
        listener: ResponseResultCallback<T>?
    ){
        listener?.let {
            return if (ex is HttpException){
                //返回结果
                listener.onFailed(ex.code().toString(), ex.message())
            }else{
                //解析异常信息
                var errorTxt = ResUtil.getString(R.string.net_msg_not_http_exception)
                listener.onFailed(
                    notHttpException.toString(), //错误码
                    "$errorTxt, 具体错误是\n${ex.message}" //错误信息
                )
            }
        }
    }
}