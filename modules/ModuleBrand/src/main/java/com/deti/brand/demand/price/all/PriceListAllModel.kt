package com.deti.brand.demand.price.all

import com.deti.brand.BrandApiService
import com.deti.brand.demand.price.all.entity.DemandIndentListApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class PriceListAllModel: BaseModel(){

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)


    var testData = "{\"result\":true,\"code\":\"00\",\"data\":{\"pageData\":{\"content\":[{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[10]\",\"deliveryTime\":\"2021-02-28 00:00:00\",\"images\":[],\"indentId\":\"lixtext12\",\"orderTime\":\"2021-01-29 11:22:44下单\",\"orderTimestamp\":1611890564,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"13\",\"quotePrice\":\"100.0\",\"receiveFlag\":\"30\",\"receiveTime\":\"2021-01-31 11:26:52签收\",\"receiveTimestamp\":1612063612,\"serialNumber\":\"2021012909465962844901\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":12,\"statusName\":\"待报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"\",\"deliveryTime\":\"2021-02-24 00:00:00\",\"images\":[{\"path\":\"\",\"type\":\"front\"},{\"path\":\"\",\"type\":\"detail\"}],\"indentId\":\"40288a8b7761c8d3017761cc7c060001\",\"orderTime\":\"2021-02-02 16:12:07下单\",\"orderTimestamp\":1612253527,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020216120703981688\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[100],测试白[60]\",\"deliveryTime\":\"2021-02-10 00:00:00\",\"images\":[{\"path\":\"\",\"type\":\"front\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"}],\"indentId\":\"40288a8b7761dd65017761de0bb90001\",\"orderTime\":\"2021-02-02 16:31:18下单\",\"orderTimestamp\":1612254678,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"10\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020216311794593166\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":4,\"statusName\":\"待报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[1]\",\"deliveryTime\":\"2021-02-27 00:00:00\",\"images\":[],\"indentId\":\"40288a8b776203f20177620c0cf80003\",\"orderTime\":\"2021-02-02 17:21:33下单\",\"orderTimestamp\":1612257693,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020217213291369058\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[67]\",\"deliveryTime\":\"2021-02-27 00:00:00\",\"images\":[],\"indentId\":\"40288a8b776203f20177621d04950014\",\"orderTime\":\"2021-02-02 17:40:05下单\",\"orderTimestamp\":1612258805,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020217400488540903\",\"service\":\"生产\",\"serviceType\":\"bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[1]\",\"deliveryTime\":\"2021-02-27 00:00:00\",\"images\":[],\"indentId\":\"40288a8b776203f201776224a3300024\",\"orderTime\":\"2021-02-02 17:48:24下单\",\"orderTimestamp\":1612259304,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020217482424011794\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[1],测试白[2]\",\"deliveryTime\":\"2021-02-27 00:00:00\",\"images\":[],\"indentId\":\"40288a8b776233cf0177623d6ebe0003\",\"orderTime\":\"2021-02-02 18:15:29下单\",\"orderTimestamp\":1612260929,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020218152919812806\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[100],测试白[30]\",\"deliveryTime\":\"2021-02-10 00:00:00\",\"images\":[{\"path\":\"\",\"type\":\"front\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"}],\"indentId\":\"40288a8b776233cf0177624a13bb001c\",\"orderTime\":\"2021-02-02 18:29:18下单\",\"orderTimestamp\":1612261758,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"10\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020218291788356553\",\"service\":\"生产\",\"serviceType\":\"bulk\",\"status\":4,\"statusName\":\"待报价\"},{\"classifyName\":\"男生-袜子-臭袜子-两周吧\",\"colorStr\":\"蓝白[2],测试白[1]\",\"deliveryTime\":\"2021-02-24 00:00:00\",\"images\":[],\"indentId\":\"40288a8b776233cf0177624e7d70003d\",\"orderTime\":\"2021-02-02 18:34:07下单\",\"orderTimestamp\":1612262047,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"20\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020218340708757710\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":1,\"statusName\":\"待得体报价\"},{\"classifyName\":\"男生-袜子-臭袜子-穿了一周\",\"colorStr\":\"蓝白[340],测试白[120]\",\"deliveryTime\":\"2021-02-10 00:00:00\",\"images\":[{\"path\":\"\",\"type\":\"front\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"},{\"path\":\"\",\"type\":\"detail\"}],\"indentId\":\"40288a8b776233cf01776251c2960056\",\"orderTime\":\"2021-02-02 18:37:41下单\",\"orderTimestamp\":1612262261,\"price\":0,\"prompt\":\"样衣签收后开始计时\",\"quoteId\":\"\",\"quotePrice\":\"\",\"receiveFlag\":\"10\",\"receiveTime\":\"\",\"receiveTimestamp\":0,\"serialNumber\":\"2021020218374139883228\",\"service\":\"打版+生产\",\"serviceType\":\"sample_bulk\",\"status\":4,\"statusName\":\"待报价\"}],\"currentPage\":1,\"pageCount\":2,\"totalElements\":17}},\"message\":\"操作成功\"}"
    /**
     * 报价列表
     */
    fun findDemandIndentListAPP(
        status: String = "",
        pageIndex: Int = 1,
    ): Flow<BaseNetEntity<DemandIndentListApp?>>{
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.apply {
                put("pageSize", "10")
                put("pageIndex", pageIndex.toString())
                put("status", status)
                put("sign", "2147483647")
                put("timestamp", "2147483647")
            }
            return@flowOnIO mHttpDataSource?.findDemandIndentListAPP(body)
        } as Flow<BaseNetEntity<DemandIndentListApp?>>
    }

}