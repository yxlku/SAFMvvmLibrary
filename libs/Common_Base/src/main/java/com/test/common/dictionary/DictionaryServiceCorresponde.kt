package com.test.common.dictionary

/**
 * 对应服务
 */
fun String.dictionaryServiceCorrespondeKeyToValue(): String{
    DictionaryServiceCorresponde::class.java.enumConstants?.forEach {
        if (it.key == this) {
            return it.value
        }
    }
    return ""
}
/**
 * 对应服务
 */
enum class DictionaryServiceCorresponde(var key: String, var value: String) {

    /** 生产*/
    PRODUCE("bulk", "生产"),
    /** 打版+生产*/
    PRODUCE_PATTERN("sample_bulk", "打板+生产");



}