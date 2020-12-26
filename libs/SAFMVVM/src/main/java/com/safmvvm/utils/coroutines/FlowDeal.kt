package com.safmvvm.utils.coroutines

import kotlinx.coroutines.flow.collect
import java.util.concurrent.TimeUnit

/**
 * 倒计时处理，预处理，只需要对
 * @param countTime           倒计时时间
 * @param timeUnit            刷新的时间单位，默认以 "秒" 为单位，设置其他非秒的单位，此方法会将单位设置成 "毫秒"（换句话说，除了秒就是毫秒）
 * @param dealBeforeBlock(Long)倒计时前的操作(倒计时总时间)
 * @param dealingBlock(Long)  倒计时中操作(处理中的时间)
 * @param dealFinishedBlock   倒计时结束时的操作
 */
suspend fun flowCountDownDeal(
    countTime: Long = 0,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    dealBeforeBlock: (Long) -> Unit,
    dealingBlock: (Long) -> Unit,
    dealFinishedBlock: () -> Unit
) {
    //处理前
    dealBeforeBlock(countTime)
    //如果传入时间小于0表示结束
    if(countTime <= 0){
        dealFinishedBlock()
    }else{
        //开始处理
        flowCountdown((countTime - 1), timeUnit)
            .collect {
                //处理中
                dealingBlock(it)
                //处理结束
                if (it <= 0) {
                    dealFinishedBlock()
                }
            }
    }
}