package com.deti.brand.demand.sampleclothes.list.adapter.btn

/**
 * 按钮状态
 */
enum class StateBtnsSampleClothes(
    var id: Int,
    var text: String
) {
    /** 待发货进度*/
    PROGRESS(0, "查看进度"),
    /** 齐色*/
    COLORS(0, "我要齐色"),
    /** 物流*/
    LOGISTICS(0, "查看物流"),
    /** 评价*/
    EVALUATION(0, "确认评价"),
    /** 复版*/
    REPRINT(0, "我要复版"),
    /** 退回修改*/
    RETURN_UPDATA(0, "退回修改"),
    /** 付款*/
    PAYMENT(0, "立即付款"),
    /** 费用详情*/
    FEE_DETAILS(0, "费用详情"),


}