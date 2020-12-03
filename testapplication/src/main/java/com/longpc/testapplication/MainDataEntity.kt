package com.longpc.testapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class MainDataEntity(
    var text: String?,
    val datas: MutableList<MainData> = mutableListOf()
): Serializable

class MainData(
    val title: String?
): Serializable