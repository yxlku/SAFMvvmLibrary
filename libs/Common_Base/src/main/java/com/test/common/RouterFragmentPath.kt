package com.test.common

object RouterFragmentPath {
    /** 品牌商*/
    object ModuleBrand {
        private const val PATH: String = "/brand/"
        private const val MODULE: String = PATH + "demand/"

        /** 创建需求*/
        const val DEAMND_CREATE = MODULE + "create"
    }


}