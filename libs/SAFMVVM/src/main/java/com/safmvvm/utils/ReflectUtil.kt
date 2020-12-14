package com.safmvvm.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 反射相关工具类
 */
object ReflectUtil {

    inline fun <reified T> getKType(K: Any,position:Int):T {
        var parameterizedType = K::class.java.genericSuperclass as ParameterizedType
        var actualTypeArguments = parameterizedType.actualTypeArguments
        return ( actualTypeArguments[position].javaClass as Class<T>).newInstance()
    }
    /**
     * 类初始化
     */
    fun <T> instance(clz: Class<T>): T{
        return clz.newInstance()
    }
}