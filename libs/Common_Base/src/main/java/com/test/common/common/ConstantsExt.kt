package com.test.common.common

import com.safmvvm.utils.KVCacheUtil

/**
 * 用户信息：token
 */
fun userInfoToken(): String? = KVCacheUtil.getString(Constants.CacheKey.C_TOKEN)