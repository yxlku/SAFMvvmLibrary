package com.test.common.common

import com.safmvvm.utils.KVCacheUtil
import com.test.common.common.entity.UserInfoEntity

/**
 * 通用方法
 */
class ConstantsFun {

    /**
     * 用户信息
     */
    object User{

        /**
         * 登录需要保存的信息
         */
        fun loginSaveInfo(userInfoEntity: UserInfoEntity){
            KVCacheUtil.apply {
                //过期时间
                put(Constants.CacheKey.C_EXPIRESIN, userInfoEntity.expiresIn)
                //用户token
                put(Constants.CacheKey.C_TOKEN, userInfoEntity.token)
                //用户身份
                put(Constants.CacheKey.C_IDENTITY, userInfoEntity.identity)
            }
        }

        /**
         * 注销时需要清除的缓存信息
         * Tip: 1、一般需要跟 loginSaveInfo() 方法中保存的内容保持一致
         *      2、***如果出现不一致的情况，需要强调说明***
         */
        fun logoutClearInfo(){
            KVCacheUtil.apply {
                //清除登录过期时间
                removeKey(Constants.CacheKey.C_EXPIRESIN)
                //清除toke
                removeKey(Constants.CacheKey.C_TOKEN)
                //清除身份
                removeKey(Constants.CacheKey.C_IDENTITY)
            }
        }

    }
}