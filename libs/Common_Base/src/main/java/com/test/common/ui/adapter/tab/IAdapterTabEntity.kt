package com.test.common.ui.adapter.tab

interface IAdapterTabEntity{
    /** tab名字*/
    var tabName: String
    /** 模式*/
    var mode: IAdapterTabMode
}

/**
 * 显示模式
 */
enum class IAdapterTabMode{
    /** 显示底部线*/
    MODE_LINE,
    /** 显示背景选中效果*/
    MODE_BG
}