package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPicChooseBinding
import com.deti.brand.demand.create.item.pic.SpannableGridLayoutManager.SpanInfo
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ui.dialog.pic.createDialogPhotoSelect

class ItemPicChoose(
    var mActivity: Activity?,
): QuickDataBindingItemBinder<ItemPicChooseEntity, BrandItemPicChooseBinding>() {
    var mViewMode = ItemPicChooseViewModel(mActivity)

    var mSpannableGridLayoutManager = SpannableGridLayoutManager(object : SpannableGridLayoutManager.GridSpanLookup {
        override fun getSpanInfo(position: Int): SpannableGridLayoutManager.SpanInfo {
            return if (position === 0) {
                SpanInfo(2, 2)
            } else {
                SpanInfo(1, 1)
            }
        }
    }, 4, 0.9F)

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPicChooseBinding>,
        data: ItemPicChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.entity = data
        binding.viewModel = mViewMode
        binding.executePendingBindings()

        var mAdapter = ItemPicChooseItemAdapter(mActivity, adapter)
        binding.rvPics.apply {
            layoutManager = mSpannableGridLayoutManager
            adapter = mAdapter
        }
        var list = arrayListOf<ItemPicChooseItemEntity>()
        list.add(ItemPicChooseItemEntity("款式正面图"))
        list.add(ItemPicChooseItemEntity("款式背面图"))
        list.add(ItemPicChooseItemEntity("款式细节图"))
        list.add(ItemPicChooseItemEntity("款式细节图"))
        list.add(ItemPicChooseItemEntity("款式细节图"))
        mAdapter.setList(list)
        mAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                clickChoosePic(adapter, adapter.data[position] as ItemPicChooseItemEntity)
            }
        })

    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPicChooseBinding =  BrandItemPicChooseBinding.inflate(layoutInflater, parent, false)

    fun clickChoosePic(adapter: BaseQuickAdapter<*, *>, entity: ItemPicChooseItemEntity) {
        mActivity?.apply {
            createDialogPhotoSelect(this,
                takePhotoClick = {
                    takePhoto(adapter, entity)
                },
                photoAlbumClick = {
                    photoAlbum(adapter, entity)
                }
            ).show()
        }
    }

    //相册
    private fun photoAlbum(adapter: BaseQuickAdapter<*, *>, entity: ItemPicChooseItemEntity) {
        PictureSelector.create(mActivity)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
            .theme(R.style.picture_WeChat_style)
            .isUseCustomCamera(true)// 是否使用自定义相机
            .minSelectNum(1)// 最小选择数量
            .maxSelectNum(5)// 最大图片选择数量
            .isEnableCrop(false)// 是否裁剪
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: MutableList<LocalMedia>?) {
                    result?.apply {
                        if(size > 1) {
                            for (i in 0 until size) {
                                var item: ItemPicChooseItemEntity =
                                    adapter.data[i] as ItemPicChooseItemEntity
                                var localMedia = this[i]
                                var path =
                                    if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
                                item.picPath = path
                                adapter.notifyDataSetChanged()
                            }
                        }else{
                            var localMedia = this[0]
                            var path =
                                if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
                            entity.picPath = path
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
                override fun onCancel() {
                    ToastUtil.showShortToast("取消")
                }
            })
    }


    fun takePhoto(adapter :BaseQuickAdapter<*, *>,entity: ItemPicChooseItemEntity) {
        mActivity?.apply {
            PictureSelector.create(this)
                .openCamera(PictureMimeType.ofAll())
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .theme(R.style.picture_WeChat_style)
                .isUseCustomCamera(true)// 是否使用自定义相机
                .minSelectNum(1)// 最小选择数量
                .maxSelectNum(1)// 最大图片选择数量
                .isEnableCrop(true)// 是否裁剪
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: MutableList<LocalMedia>?) {
                        result?.forEach {
                            var path =
                                if (TextUtils.isEmpty(it.androidQToPath)) it.path else it.androidQToPath
                            LogUtil.d("拍照: $path / ${it.fileName}")
                            entity.picPath = path
                            adapter.notifyDataSetChanged()
                        }
                    }

                    override fun onCancel() {
                        ToastUtil.showShortToast("取消")
                    }

                })
        }
    }
}