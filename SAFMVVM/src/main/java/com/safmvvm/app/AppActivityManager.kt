package com.safmvvm.app

import android.app.Activity
import java.util.*

/**
 * App 全局 Activity 管理器，采用 registerActivityLifecycleCallbacks 监听所有的 Activity 的创建和销毁。
 * 可通过 [GlobalConfig.gIsNeedActivityManager] 关闭这个功能
 *
 * object class 单例模式创建
 * 使用AppActivityManager
 */
object AppActivityManager {
    /** Activity栈*/
    private val mActivityStack = mutableListOf<Activity>()

    /**
     * 将Activity添加到栈中
     * @param activity 当前创建的Activity
     */
    fun add(activity: Activity) = mActivityStack.add(activity)

    /**
     * 将Activity移除栈
     * @param activity 从栈中移除Activity
     */
    fun remove(activity: Activity?){
        if (activity != null) {
            if (mActivityStack.contains(activity)) mActivityStack.remove(activity)
        }
    }

    /**
     * 栈中是否有Activity
     */
    fun isEmpty(): Boolean{
        return mActivityStack.isEmpty()
    }

    /**
     * 获取当前显示Activity
     * 原理：栈顶的Activity-也就是栈中最后加入的Activity
     */
    fun currentActivity(): Activity?{
        if (mActivityStack.isNotEmpty()) {
            return mActivityStack.last()
        }
        return null
    }

    /**
     * 获取当前Activity，没有返回null
     */
    fun getActivity(clazz: Class<Activity>): Activity? {
        return mActivityStack.find {it.javaClass == clazz }
    }

    /**
     * 关闭当前Activity
     */
    fun finishCurrentActivity(){
        currentActivity()?.finish()
        remove(currentActivity())
    }

    /**
     * 关闭指定Activity
     * @param activity 指定Activity
     * 内部判断是否包含当前Activity
     */
    fun finishActivity(activity: Activity){
        if (getActivity(activity.javaClass) != null) {
            activity?.finish()
            remove(activity)
        }
    }

    /**
     * 关闭栈中所有Activity
     */
    fun finishAllActivity(){
        mActivityStack.forEach{
            it.finish()
        }
        //清空栈
        mActivityStack.clear()
    }

}