package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPicChooseBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.demand.create.CreateDemandViewModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ext.photoAlbum
import com.test.common.ext.takePhoto
import com.test.common.ui.dialog.pic.PhotoSelectEntity
import com.test.common.ui.dialog.pic.createDialogPhotoSelect

class ItemPicChoose(
    var mActivity: Activity?,
    var mViewModel: CreateDemandViewModel
): QuickDataBindingItemBinder<ItemPicChooseEntity, BrandItemPicChooseBinding>() {

    var mAdapter = ItemPicChooseItemAdapter(mViewModel){entity: ItemPicChooseItemEntity, pos: Int->
        mViewModel.mPicListDatas[pos] = ""
        entity.picPath.set("")
    }
    var list = arrayListOf<ItemPicChooseItemEntity>(
        ItemPicChooseItemEntity("款式正面图"),
        ItemPicChooseItemEntity("款式背面图"),
        ItemPicChooseItemEntity("款式细节图"),
        ItemPicChooseItemEntity("款式细节图"),
        ItemPicChooseItemEntity("款式细节图"),
    )
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPicChooseBinding>,
        data: ItemPicChooseEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            viewModel = mViewModel
            executePendingBindings()
        }

        binding.rvPics.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                    override fun getSpanSize(position: Int): Int {
                        if(position == 0){
                            return 2
                        }else{
                            return 1
                        }
                    }
                }
            }
            adapter = mAdapter
        }
        mAdapter.setList(list)
        mAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var entity = adapter.data[position] as ItemPicChooseItemEntity
                mActivity?.apply {
//            if (mPopupChoosePic == null) {
                    mPopupChoosePic = createDialogPhotoSelect(this, 5,
                        takePhotoClick = {
                            takePhoto(adapter, entity, position)
                        },
                        photoAlbumClick = {
                            photoAlbum(adapter, entity, position)
                        },
                        photoSelectBlock = { data: PhotoSelectEntity, position: Int, view: View, popupView: BasePopupView ->
                            //选择图片
                            entity.picPath.set(data.localMedia?.path)
                            popupView.dismiss()
                        }
                    )
//            }
                    mPopupChoosePic?.show()
                }
            }
        })
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPicChooseBinding =  BrandItemPicChooseBinding.inflate(layoutInflater, parent, false)

    var mPopupChoosePic: BasePopupView? = null

    //相册
    private fun photoAlbum(adapter: BaseQuickAdapter<*, *>, entity: ItemPicChooseItemEntity, pos: Int) {
        mActivity?.photoAlbum(
            block = {result: MutableList<LocalMedia>?->
                result?.apply {
                    if(size > 1) {
                        for (i in 0 until size) {
                            var item: ItemPicChooseItemEntity =
                                adapter.data[i] as ItemPicChooseItemEntity
                            var localMedia = this[i]
                            var path =
                                if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
                            item.picPath.set(path)
                        }
                    }else{
                        var localMedia = this[0]
                        var path = if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
//                        entity.picPath.set(path)
//                        mViewModel.mPicListDatas[pos] = path
                        LiveDataBus.send(CreateDemandFragment.PIC_CHOOSE, Triple(entity, path, pos))
                    }
                }
            },
            cancel = {
                ToastUtil.showShortToast("取消")
            }
        )
    }


    fun takePhoto(adapter :BaseQuickAdapter<*, *>,entity: ItemPicChooseItemEntity, pos: Int) {
        mActivity?.takePhoto(
            block = {result: MutableList<LocalMedia>?->
                result?.forEach {
                    var path =
                        if (TextUtils.isEmpty(it.androidQToPath)) it.path else it.androidQToPath
                    LogUtil.d("拍照: $path / ${it.fileName}")
//                    entity.picPath.set(path)
//                    mViewModel.mPicListDatas[pos] = path
                    LiveDataBus.send(CreateDemandFragment.PIC_CHOOSE, Triple(entity, path, pos))
                }
            },
            cancel = {
                ToastUtil.showShortToast("取消")
            }
        )
    }
}