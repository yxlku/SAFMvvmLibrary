package com.longpc.testapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MainDataEntity(
    var text: String?,
    //TODO 要写什么类型的集合
    var datas: MutableList<MainData>
): Parcelable

@Parcelize
data class MainData(
    var title: String?
): Parcelable