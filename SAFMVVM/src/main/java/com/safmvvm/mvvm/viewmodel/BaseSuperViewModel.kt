package com.safmvvm.mvvm.viewmodel

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.safmvvm.R
import com.safmvvm.app.GlobalConfig
import com.safmvvm.app.RepositoryManager
import com.safmvvm.http.HttpDeal
import com.safmvvm.http.entity.IBaseResponse
import com.safmvvm.http.result.ResponseResultCallback
import com.safmvvm.http.result.state.entityNullable
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.ui.load.LoadState
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.ResUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * viewModel基本基类父类，不带有UI方面的监听传递操作，需要UI赋能的继承BaseViewModel
 * 管理model的创建、缓存、声明周期
 */
abstract class  BaseSuperViewModel<M: BaseModel>(app: Application): AndroidViewModel(app), IViewModel{

    /**
     * 手动创建Model后传入
     * 不自动创建仓库
     */
    constructor(app: Application, model: M): this(app){
        this.mIsAutoCreateRepo = false
        this.mModel = model
    }
    /**
     * 可能存在没有仓库的 vm，但我们这里也不要是可 null 的。
     * 如果 vm 没有提供仓库，说明此变量不可用，还去使用的话自然就报错。
     */
    lateinit var mModel: M
    /** 是否自动创建仓库，默认是 true constructor(app: Application, model: M) 将不自动创建仓库*/
    private var mIsAutoCreateRepo = true
    /** 请求栈*/
    private lateinit var mCallList: MutableList<Call<*>>

    /** 是否缓存自动创建的仓库，默认是 true */
    protected open fun isCacheRepo() = true

    override fun onCreate(owner: LifecycleOwner) {
        if (mIsAutoCreateRepo) {
            //自动创建Model
            if (!this::mModel.isInitialized) {
                //返回当前对象所表示的类的超类,getGenericSuperclass会包含该超类的泛型。
                //能取出指定的泛型
                val type: Type? = javaClass.genericSuperclass
                //https://www.cnblogs.com/baiqiantao/p/7460580.html
                //ParameterizedType 泛型/参数化类型【重要】
                var modelClass: Class<M>? = if(type is ParameterizedType){
                    //取得第一个泛型
                    type.actualTypeArguments[0] as? Class<M>
                }else null
                if(modelClass != null && modelClass != BaseModel::class.java){
                    mModel = RepositoryManager.getRepo(modelClass, isCacheRepo())
                }
            }
        }
    }

    /**
     * Old：无要求请求模式
     * 所有网络请求都在 mCoroutineScope 域中启动协程，当页面销毁时会自动取消
     */
    fun <T: Parcelable> launchNet(
        block: suspend CoroutineScope.() -> IBaseResponse<T?>?,
        listener: ResponseResultCallback<T>?
    ): Job{
        return viewModelScope.launch {
            //请求时等待的操作
            listener?.onLoading(GlobalConfig.Request.gLodingMsg)
            //处理结果
            try {
                //处理请求结果
                HttpDeal.dealResult(block(), listener)
            } catch (ex: Exception) {
                //处理异常
                HttpDeal.dealException(ex, listener)
            } finally {
                //操作完成 -- 执行一些取消等待操作
                listener?.onComplete()
            }
        }
    }

    /**
     * New:
     * 通常用在ViewModel层进行处理请求后的统一操作
     * 异步操作
     * 1、异步前操作
     * 2、异步完成操作
     * 3、成功后操作
     * 4、失败后操作
     */
    suspend fun <T> Flow<T>.flowDataDeal(
        /** 等待状态 */
        loadingModel: LoadingModel = LoadingModel.LOADSIR,
        /** 成功状态 */
        onSuccess: (T)->Unit,
        /** 请求成功但是返回错误*/
        onFaile: (code: String, msg: String) -> Unit,
        /** 错误状态、不能成功请求（无网络） */
        onError: (ex: Throwable) -> Unit?
    ){
        //基类所有操作都是遵循：1、统一封装；2、调用回调函数到调用者返回去处理自定义操作
        this.onStart {
                //等待处理
                showLoadPageState(loadingModel, LoadState.LOADING)
            }
            .catch {
                //异常处理
                LogUtil.exception("请求异常", it)
                onError(it)
                showLoadPageState(loadingModel, LoadState.NET_ERROR, isModify = true, msg = "没有网别点了！", subMsg = "真没网")
            }
            .collect {
                //返回数据处理成功与失败结果
                if (it is IBaseResponse<*>) {
                    if (it == null) {
                        //提示实体为空
                        showLoadPageState(loadingModel, LoadState.EMPTY)
                        return@collect
                    }
                    val code = it.code()
                    val msg = it.msg()
                    val data: T? = it.data() as T?
                    if (code.isEmpty()) {
                        //code为空
                        showLoadPageState(loadingModel, LoadState.FAIL)
                        return@collect
                    }
                    if (data == null) {
                        //数据为空
                        showLoadPageState(loadingModel, LoadState.EMPTY)
                        return@collect
                    }
                    //结果正常处理
                    if (code == GlobalConfig.Request.SUCCESS_CODE) {
                        //返回成功
                        showLoadPageState(loadingModel, LoadState.SUCCESS)
                        onSuccess(data)
                    }else{
                        //请求成功但服务器返回错误
                        showLoadPageState(loadingModel, LoadState.FAIL, isModify = true, msg = msg)
                    }
                }else{
                    //数据异常
                    showLoadPageState(loadingModel, LoadState.FAIL, isModify = true, msg = "数据异常")
                }
            }

    }

    /**
     * 1、在Model层处理好得到Flow数据
     * 2、调用此方法即可得地请求统一封装操作
     */
    inline fun launchRequest(crossinline block: suspend CoroutineScope.()-> Unit){
        viewModelScope.launch {
            block()
        }
    }
    /**
     * 发起协程，让协程和 UI 相关
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    /**
     * 发起流
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    /**
     * 使用 Retrofit 原生的请求方式
     */
    fun addCall(call: Any){
        if (!this::mCallList.isInitialized) {
            mCallList = mutableListOf()
        }
        mCallList.add(call as Call<*>)
    }
    /**
     * 取消耗时任务，比如在界面销毁时，或者在对话框消失时
     */
    fun cancelConsumingTask() {
        if (this::mCallList.isInitialized) {
            mCallList.forEach {
                it.cancel()
            }
            mCallList.clear()
        }
        //当 ViewModel 清空时取消所有协程
        viewModelScope.cancel()
    }
    /**
     * viewmodel不在使用时调用，取消一切订阅操作
     */
    override fun onCleared() {
        //可能 mModel 是未初始化的
        if (this::mModel.isInitialized) {
            //model执行取消绑定的声明周期
            mModel.onCleared()
        }
        //取消请求、取消协程
        cancelConsumingTask()
    }

}
