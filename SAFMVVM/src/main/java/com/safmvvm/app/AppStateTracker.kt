package com.safmvvm.app

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * App 状态监听器，可用于判断应用是在后台还是在前台
 */
object AppStateTracker : LifecycleObserver {
    //是否打开监听
    private var mIsTract = false
    private var mChangeListener: MutableList<AppStateChangeListener> = mutableListOf()

    /** 状态：前台*/
    const val STATE_FOREGROUND = 0

    /** 状态: 后台*/
    const val STATE_BACKGROUND = 1

    /** 当前状态*/
    var currentState = STATE_FOREGROUND
        get() {
            if (!mIsTract) {
                throw RuntimeException("必须先调用 track 方法")
            }
            return field
        }

    /**
     * app监听状态
     */
    interface AppStateChangeListener {
        /** app进入到了前台 */
        fun appTurnIntoForeground()
        /** app进入了后台 */
        fun appTurnIntoBackground()
    }

    /**
     * 设置应用状态监听
     */
    fun track(appStateChangeListener: AppStateChangeListener) {
        //没开启则开启追踪，并初始化LifeCycyle
        if (!mIsTract) {
            mIsTract = true
            ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        }
        //添加监听
        mChangeListener.add(appStateChangeListener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForeground(){
        //app进入前台
        currentState = STATE_FOREGROUND
        mChangeListener.forEach {
            //前台回调
            it.appTurnIntoForeground()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackground() {
        //app进入后台
        currentState = STATE_BACKGROUND
        mChangeListener.forEach {
            //后台回调
            it.appTurnIntoBackground()
        }
    }


}