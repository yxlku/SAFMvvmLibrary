package com.safmvvm.utils

import android.app.Application
import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*

object KVCacheUtil {
    var mmkv: MMKV? = null

    fun init(app: Application){
        MMKV.initialize(app)
        mmkv = MMKV.defaultMMKV()
    }

    /**
     * 存入基本对象
     */
    fun put(key: String, value: Any?) {
        when (value) {
            is String -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Int -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Nothing -> return
        }
    }

    /**
     * 存入序列化对象
     */
    fun <T : Parcelable> put(key: String, t: T?) {
        if (t == null) {
            return
        }
        mmkv?.encode(key, t)
    }

    /**
     * 存入没有序列号的对象
     */
    fun  putAny(key: String, any: Any){
        any.let {
            var anyJson = JsonUtil.toJson(any)
            mmkv?.putString(key, anyJson)
        }
    }

    /**
     * 存入set集合
     */
    fun put(key: String, sets: Set<String>?) {
        if (sets == null) {
            return
        }
        mmkv?.encode(key, sets)
    }

    /**
     * 存入map集合
     */
    fun <K, V> put(key: String, map: Map<K, V>?) {
        map?.let {
            var mapJson: String = JsonUtil.toJson(it)
            mapJson.let {
                put(key, it)
            }
        }
    }

    /**
     * 存入List集合
     */
    fun <T> put(key: String, list: List<T>) {
        list.let {
            var listJson: String = JsonUtil.toJson(it)
            listJson.let {
                put(key, listJson)
            }
        }
    }

    fun getInt(key: String): Int? {
        return mmkv?.decodeInt(key, 0)
    }

    fun getDouble(key: String): Double? {
        return mmkv?.decodeDouble(key, 0.00)
    }

    fun getLong(key: String): Long? {
        return mmkv?.decodeLong(key, 0L)
    }

    fun getBoolean(key: String): Boolean? {
        return mmkv?.decodeBool(key, false)
    }

    fun getFloat(key: String): Float? {
        return mmkv?.decodeFloat(key, 0F)
    }

    fun getByteArray(key: String): ByteArray? {
        return mmkv?.decodeBytes(key)
    }

    fun getString(key: String): String? {
        return mmkv?.decodeString(key, "")
    }

    fun <T : Parcelable> getParcelable(key: String, tClass: Class<T>): T? {
        return mmkv?.decodeParcelable(key, tClass)
    }

    fun getStringSet(key: String): Set<String>? {
        return mmkv?.decodeStringSet(key, Collections.emptySet())
    }

    /**
     * 获取到非序列化的对象
     */
    fun <T> getAny(key: String, classT: Class<T>): T?{
        var jsonResult = getString(key)
        jsonResult?.let {
            return JsonUtil.getJsonParseResult(jsonResult, classT)
        }
        return null
    }

    /**
     * 获取map
     */
    fun getMap(key: String): Map<String, Any?>? {
        var jsonResult: String? = getString(key)
        jsonResult?.let {
            return JsonUtil.getJsonParseMapResult(it)
        }
        return null
    }

    /**
     * 获取list
     */
    fun <T> getList(key: String, classT: Class<T>): List<T>? {
        var jsonResult: String? = getString(key)
        jsonResult?.let {
            return JsonUtil.getJsonParseArrayResult(it, classT)
        }
        return null
    }

    fun removeKey(key: String) {
        mmkv?.removeValueForKey(key)
    }


    fun clearAll() {
        mmkv?.clearAll()
    }
}