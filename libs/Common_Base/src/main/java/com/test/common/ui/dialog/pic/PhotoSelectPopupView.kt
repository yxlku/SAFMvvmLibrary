package com.test.common.ui.dialog.pic

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.entity.LocalMediaFolder
import com.luck.picture.lib.listener.OnQueryDataResultListener
import com.luck.picture.lib.model.LocalMediaPageLoader
import com.lxj.xpopup.core.BottomPopupView
import com.safmvvm.utils.LogUtil
import com.test.common.R

class PhotoSelectPopupView(
    var mActivit: Activity,
    /** 拍照*/
    var takePhotoClick: () -> Unit = {},
    /** 相册*/
    var photoAlbumClick: () -> Unit = {},
) : BottomPopupView(mActivit), View.OnClickListener {

    override fun getImplLayoutId(): Int = R.layout.base_dialog_photo_select

    fun testData() {
        LocalMediaPageLoader.getInstance(mActivit).loadPageMediaData(-1,
            1,
            object : OnQueryDataResultListener<LocalMedia> {
                override fun onComplete(
                    data: MutableList<LocalMedia>?,
                    currentPage: Int,
                    isHasMore: Boolean,
                ) {
                    var newData: MutableList<LocalMedia> = mutableListOf()
                    data?.forEach {
                        if (PictureMimeType.isHasImage(it.mimeType)) {
                            newData.add(it)
                        }
                    }
                    itemAdapter.setList(newData)
                    LogUtil.d("选择的图片：${data?.size}")
                }

            })


    }

    var itemAdapter = DialogPhotSelectItemAdapter()
    override fun onCreate() {
        super.onCreate()
        testData()
        var tv_close: TextView = findViewById(R.id.tv_close)
        var tv_take_photo: TextView = findViewById(R.id.tv_take_photo)
        var tv_photo_album: TextView = findViewById(R.id.tv_photo_album)
        var rv_pics: RecyclerView = findViewById(R.id.rv_pics)
        tv_close.setOnClickListener(this)
        tv_take_photo.setOnClickListener(this)
        tv_photo_album.setOnClickListener(this)

        rv_pics.apply {
            layoutManager = LinearLayoutManager(mActivit).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = itemAdapter
        }
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