package com.test.common.dictionary

/**
 * 通过key找到value
 */
fun String.dictionaryDemandTypeKeyToValue(): String{
    DictionaryDemandType::class.java.enumConstants?.forEach {
        if (it.key == this) {
            return it.value
        }
    }
    return ""
}

/**
 * 需求类型
 */
enum class DictionaryDemandType(val key: String, val value: String) {

    /** 图片*/
    TYPE_PICTURE("picture", "图片"),

    /** 样衣*/
    TYPE_SAMPLE("sample", "样衣"),

    /** 面料信息*/
    TYPE_FABRIC("fabric", "面料信息"),

    /** 设计稿*/
    TYPE_LAYOUT("layout", "设计稿"),

    /** 制版文件*/
    TYPE_PRODUCTION_STANDARD("production_standard", "制版文件")
}