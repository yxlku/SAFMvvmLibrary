package com.deti.brand.demand.create.item.file

import androidx.databinding.ObservableField

/**
 * 上传文件
 */
data class ItemUploadFileEntity(
    var tag: Int = -1,
    var title: String = "",
    var tipContent: String = "",
    var btnText: String = "",
    /** 文件地址*/
    var filePath: ObservableField<String> = ObservableField<String>()
)

/**
 * 上传文件类型
 */
object ItemUploadFileEnum{
    /** 面料文件*/
    var FILE_FABRIC = 0
    /** 制版文件*/
    var FILE_PLATE = 1
}