package com.test.common.ui.dialog.pic

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnQueryDataResultListener
import com.luck.picture.lib.model.LocalMediaPageLoader
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.R

class PhotoSelectPopupView(
    var mActivit: Activity,
    /** 最多选几个*/
    var maxSelectCount: Int = 5,
    /** 拍照*/
    var takePhotoClick: () -> Unit = {},
    /** 相册*/
    var photoAlbumClick: () -> Unit = {},
    /** 点击图片*/
    var photoSelectBlock: (data: PhotoSelectEntity, position: Int, view: View, popupView: BasePopupView) -> Unit = {data: PhotoSelectEntity, position: Int, view: View, popupView: BasePopupView->}
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
                    var newData: MutableList<PhotoSelectEntity> = mutableListOf()
                    data?.forEach {
                        if (PictureMimeType.isHasImage(it.mimeType)) {
                            var data = PhotoSelectEntity(it, false)
                            newData.add(data)
                        }
                    }
                    itemAdapter.setList(newData)
                    LogUtil.d("选择的图片：${data?.size}")
                }

            })
    }

    /**
     * 选中了几个
     */
    fun selectCount(): Int{
        var selectSize = 0
        itemAdapter.data.forEach {
            if(it.isSelect) {
                selectSize += 1
            }
        }
        return selectSize
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
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = itemAdapter
        }

        itemAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var data: PhotoSelectEntity = adapter.data[position] as PhotoSelectEntity
                if(selectCount() < maxSelectCount || data.isSelect) {
                    data.isSelect = !data.isSelect
                    itemAdapter.notifyDataSetChanged()
                    photoSelectBlock(data, position, view, this@PhotoSelectPopupView)
                }else{
                    ToastUtil.showShortToast("最多选 ${selectCount()} 张")
                }
            }
        })
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