package com.safmvvm.mvvm.args

import android.os.Parcelable
import com.kingja.loadsir.callback.Callback
import kotlinx.android.parcel.Parcelize

/**
 * 嵌入页面需要的信息
 */
@Parcelize
data class LoadSirUpdateMsgEntity(
    val callBack: Class<out Callback>?,
    /** 是否需要修改*/
    val isModify: Boolean,
    /** 修改信息*/
    val msg: String?,
    /** 修改子标题*/
    val subMsg: String?,
    /** 返回Code码*/
    val code: String,
    /** 图标*/
    val icon: Int
):Parcelable
