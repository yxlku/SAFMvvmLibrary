package com.safmvvm.bus

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.safmvvm.utils.AppUtil
import com.safmvvm.utils.LogUtil
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 设置Value，不用判断线程来选择方法了
 */
fun <T> SingleLiveEvent<T>.putValue(pValue: T){
    if (AppUtil.isInMainThread()) {
        this.value = pValue
    }else{
        this.postValue(pValue)
    }
}

/**
 * 只有一个观察者能收到通知，并且只有明确调用了 setValue 的时候才会发出通知。
 * 反复注册观察并不会触发重新通知。
 */
class SingleLiveEvent<T> : MutableLiveData<T?>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in T?>
    ) {
        if (hasActiveObservers()) {
            LogUtil.w(
                TAG,
                "Multiple observers registered but only one will be notified of changes."
            )
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t: T? ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}