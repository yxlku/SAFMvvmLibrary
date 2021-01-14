package com.test.common.ui.dialog.pic

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.lxj.xpopup.core.BottomPopupView
import com.test.common.R

class PhotoSelectPopupView(
    var mActivit: Activity,
    /** 拍照*/
    var takePhotoClick: () -> Unit = {},
    /** 相册*/
    var photoAlbumClick: () -> Unit = {},
) : BottomPopupView(mActivit), View.OnClickListener {

    override fun getImplLayoutId(): Int = R.layout.base_dialog_photo_select

    override fun onCreate() {
        super.onCreate()

        var tv_close: TextView = findViewById(R.id.tv_close)
        var tv_take_photo: TextView = findViewById(R.id.tv_take_photo)
        var tv_photo_album: TextView = findViewById(R.id.tv_photo_album)
        tv_close.setOnClickListener(this)
        tv_take_photo.setOnClickListener(this)
        tv_photo_album.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            //关闭
            R.id.tv_close -> dismiss()
            //拍照
            R.id.tv_take_photo -> {
                takePhotoClick()
                dismiss()
            }
            //相册
            R.id.tv_photo_album -> {
                photoAlbumClick()
                dismiss()
            }
        }
    }


}