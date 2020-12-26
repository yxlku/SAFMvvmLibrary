package com.test.common

object RouterActivityPath {

    object ModuleBasis{
        private const val PATH: String = "/basis/"

        /** 登录页面*/
        const val PAGE_LOGIN: String = PATH + "login"
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