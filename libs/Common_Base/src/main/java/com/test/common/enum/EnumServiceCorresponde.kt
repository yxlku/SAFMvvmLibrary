package com.test.common.enum

/**
 * 对应服务
 */
enum class EnumServiceCorresponde(var key: String, var value: String) {

    /** 生产*/
    PRODUCE("bulk", "生产"),
    /** 打版+生产*/
    PRODUCE_PATTERN("sample_bulk", "打板+生产");

    fun EnumServiceType.keyToValue(key: String): String{
        EnumServiceType::class.java.enumConstants?.forEach {
            if (it.key == key) {
                return it.value
            }
        }
        return ""
    }

}