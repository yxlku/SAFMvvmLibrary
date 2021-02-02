package com.test.common.dictionary

/**
 * 通过key找到value
 */
fun String.dictionaryServiceTypeKeyToValue(): String{
    DictionaryServiceType::class.java.enumConstants?.forEach {
        if (it.key == this) {
            return it.value
        }
    }
    return ""
}
/**
 * 服务类型
 */
enum class DictionaryServiceType(var key: String, var value: String) {

    /** 生产*/
    PRODUCE("fob", "包工包料"),
    /** 打版+生产*/
    PRODUCE_PATTERN("cmt", "纯加工");



}