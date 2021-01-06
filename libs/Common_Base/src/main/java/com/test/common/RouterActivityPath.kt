package com.test.common

object RouterActivityPath {

    /** 基础通用*/
    object ModuleBasis{
        private const val PATH: String = "/basis/"

        /** 登录页面*/
        const val PAGE_LOGIN: String = PATH + "login"
    }
    /** 品牌商*/
    object ModuleBrand {
        private const val PATH: String = "/brand/"

        /** testA*/
        const val PAGE_MAIN = PATH + "main"
    }


    object ModuleTestA {
        private const val PATH: String = "/testA/"

        /** testA*/
        const val PAGE_TESTA = PATH + "page_testA"
    }

    object TestApplication {
        private const val PATH: String = "/main/"

        /** testApplication的首页*/
        const val PAGE_MAIN = PATH + "page_main"
    }

}