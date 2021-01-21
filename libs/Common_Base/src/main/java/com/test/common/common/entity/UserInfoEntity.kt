package com.test.common.common.entity

import java.io.Serializable

/**
 * 用户登录信息
 */
data class UserInfoEntity(
    /** Token 过期时间 Long*/
    var expiresIn: String = "",
    /** 身份*/
    var identity: String = "",
    /** token*/
    var token: String = "",
): Serializable