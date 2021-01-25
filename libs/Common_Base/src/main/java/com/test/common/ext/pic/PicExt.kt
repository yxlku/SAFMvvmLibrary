package com.test.common.ext

import android.app.Activity
import android.graphics.Picture
import android.text.TextUtils
import com.luck.picture.lib.PictureSelectionModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.R
import com.test.common.ext.pic.GlideEngine


/**
 * 拍照
 */
fun Activity.takePhoto(
    block: (result: MutableList<LocalMedia>?) -> Unit = {},
    cancel: () -> Unit = {},
    configProperty: (pictureSelectionModel: PictureSelectionModel) -> Unit = {pictureSelectionModel: PictureSelectionModel->}
){
    PictureSelector.create(this)
        .openCamera(PictureMimeType.ofAll())
        .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
        .theme(R.style.picture_default_style)
        .isUseCustomCamera(true)// 是否使用自定义相机
        .minSelectNum(1)// 最小选择数量
        .maxSelectNum(1)// 最大图片选择数量
        .isEnableCrop(true)// 是否裁剪
        .apply {
            configProperty(this)
        }
        .forResult(object : OnResultCallbackListener<LocalMedia> {
            override fun onResult(result: MutableList<LocalMedia>?) {
                block(result)
            }

            override fun onCancel() {
                cancel()
            }
        })
}

/**
 * 打开相册
 */
fun Activity.photoAlbum(
    block: (result: MutableList<LocalMedia>?) -> Unit = {},
    cancel: () -> Unit = {},
    configProperty: (pictureSelectionModel: PictureSelectionModel) -> Unit = {}
){
    PictureSelector.create(this)
        .openGallery(PictureMimeType.ofImage())
        .imageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
        .theme(R.style.picture_default_style)
        .isUseCustomCamera(true)// 是否使用自定义相机
        .minSelectNum(1)// 最小选择数量
        .maxSelectNum(5)// 最大图片选择数量
        .isEnableCrop(false)// 是否裁剪
        .apply {
            configProperty(this)
        }
        .forResult(object : OnResultCallbackListener<LocalMedia> {
            override fun onResult(result: MutableList<LocalMedia>?) {
                block(result)
            }

            override fun onCancel() {
                cancel()
            }
        })
}