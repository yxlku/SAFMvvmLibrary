package com.test.common.enum


/**
 * 服务类型
 */
enum class EnumServiceType(var key: String, var value: String) {

    /** 生产*/
    PRODUCE("fob", "包工包料"),
    /** 打版+生产*/
    PRODUCE_PATTERN("cmt", "纯加工");

    fun EnumServiceType.keyToValue(key: String): String{
        EnumServiceType::class.java.enumConstants?.forEach {
            if (it.key == key) {
                return it.value
            }
        }
        return ""
    }

}