package com.test.common.entity

/**
 * 通用需求图片图片实体类，包含图片 地址 和 图片类型（正面照、背面照、其他。。）
 */
data class CommonDeamndImageEntity(
    var path: String = "",
    /** 图片类型：front = 主图、detail = 详情图*/
    var type: String = "",
)