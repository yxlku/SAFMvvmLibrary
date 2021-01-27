package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import cn.ucloud.ufile.UfileClient
import cn.ucloud.ufile.api.`object`.GenerateObjectPrivateUrlApi
import cn.ucloud.ufile.api.`object`.ObjectApiBuilder
import cn.ucloud.ufile.api.`object`.ObjectConfig
import cn.ucloud.ufile.auth.ObjectRemoteAuthorization.ApiConfig
import cn.ucloud.ufile.auth.UfileObjectRemoteAuthorization
import cn.ucloud.ufile.exception.UfileClientException
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.deti.brand.databinding.BrandItemPicChooseBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.demand.create.CreateDemandViewModel
import com.luck.picture.lib.entity.LocalMedia
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ext.photoAlbum
import com.test.common.ext.takePhoto
import com.test.common.ui.dialog.pic.PhotoSelectEntity
import com.test.common.ui.dialog.pic.createDialogPhotoSelect
import java.io.File

class ItemPicChoose(
    var mActivity: Activity?,
    var mViewModel: CreateDemandViewModel,
): QuickDataBindingItemBinder<ItemPicChooseEntity, BrandItemPicChooseBinding>() {

    var mAdapter = ItemPicChooseItemAdapter(mViewModel){ entity: ItemPicChooseItemEntity, pos: Int->
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
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
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
    private fun photoAlbum(
        adapter: BaseQuickAdapter<*, *>,
        entity: ItemPicChooseItemEntity,
        pos: Int,
    ) {
        mActivity?.photoAlbum(
            block = { result: MutableList<LocalMedia>? ->
                result?.apply {
                    if (size > 1) {
                        for (i in 0 until size) {
                            var item: ItemPicChooseItemEntity =
                                adapter.data[i] as ItemPicChooseItemEntity
                            var localMedia = this[i]
                            var path =
                                if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
                            item.picPath.set(path)
                        }
                    } else {
                        var localMedia = this[0]
                        var path =
                            if (TextUtils.isEmpty(localMedia.androidQToPath)) localMedia.path else localMedia.androidQToPath
//                        entity.picPath.set(path)
//                        mViewModel.mPicListDatas[pos] = path
                        testpPic(path)
                        LiveDataBus.send(CreateDemandFragment.PIC_CHOOSE, Triple(entity, path, pos))
                    }
                }
            },
            cancel = {
                ToastUtil.showShortToast("取消")
            }
        )
    }

    fun takePhoto(adapter: BaseQuickAdapter<*, *>, entity: ItemPicChooseItemEntity, pos: Int) {
        mActivity?.takePhoto(
            block = { result: MutableList<LocalMedia>? ->
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


    private fun testpPic(path: String?) {
        var authToken = "TOKEN_696b1902-38dd-411f-b9f6-18c454ec200c"
        var authUrlPublic = "http://192.168.10.11:9101/UCloud/applyAuth"
        var authUrlPrivate = "http://192.168.10.11:9101/UCloud/applyPrivateUrlAuth"
        var proxySuffix = "deti.cn-sh2.ufileos.com"

        var objectConfig = ObjectConfig(proxySuffix)

        var authorization = UfileObjectRemoteAuthorization(authToken, ApiConfig(authUrlPublic, authUrlPrivate))
        var objectApiBuilder: ObjectApiBuilder = UfileClient.`object`(authorization, objectConfig) //

        var file = File(path)

        //私有地址 只能通过文件路径+地址获取
        objectApiBuilder.getDownloadUrlFromPrivateBucket("image/1606887532190.jpeg",
            "deti",
            60 * 60 * 24)
            .withIopCmd("iopcmd=rotate&degree=180|iopcmd=thumbnail&type=1&scale=40|iopcmd=watermark&type=1|")
            .createUrlAsync(object : GenerateObjectPrivateUrlApi.CreatePrivateUrlCallback {
                override fun onSuccess(url: String?) {
                    LogUtil.d("私有地址：$url")
                }

                override fun onFailed(e: UfileClientException?) {
                }
            })

//        //上传
//            objectApiBuilder.putObject(file, MimeTypeUtil.getMimeType(file.getName()))
//                .nameAs("image/${file.name}")
//                .toBucket("deti")
//                .executeAsync(object : UfileCallback<PutObjectResultBean>() {
//                    override fun onResponse(p0: PutObjectResultBean?) {
//                        LogUtil.d("上传信息：${p0}")
//                        //私有地址 只能通过文件路径+地址获取
//                        objectApiBuilder.getDownloadUrlFromPrivateBucket("image/1606887532190.jpeg", "deti", 60*60*24)
//                            .createUrlAsync(object : GenerateObjectPrivateUrlApi.CreatePrivateUrlCallback {
//                                override fun onSuccess(url: String?) {
//                                    LogUtil.d("私有地址：$url")
//                                }
//                                override fun onFailed(e: UfileClientException?) {
//                                }
//                            })
//                        //公有地址
//                        var eTag = p0?.geteTag()
//                        var path = objectApiBuilder.getDownloadUrlFromPublicBucket(eTag, "deti")
//                            .createUrl()
//                        LogUtil.d("公有地址：$path")
//                        if (p0 != null && p0.retCode == 0) {
//                            LogUtil.d("上传成功：${file.name}")
//                        } else {
//                            LogUtil.d("上传失败")
//                        }
//                    }
//
//                    override fun onError(p0: Request?, p1: ApiError?, p2: UfileErrorBean?) {
//                        LogUtil.d("上传失败：${p1}")
//                    }
//
//                })


        //查询指定文件
//        objectApiBuilder.objectProfile("image/1606887532190.jpeg", "deti")
//            .executeAsync(object : UfileCallback<ObjectProfile>(){
//                override fun onResponse(p0: ObjectProfile?) {
//                    LogUtil.d("文件信息：${p0}")
//
//                    //查询指定数据
//                    var path = objectApiBuilder.getDownloadUrlFromPublicBucket(p0?.keyName, "deti")
//                        .createUrl()
//                    LogUtil.d("公有地址：$path")
//                }
//
//                override fun onError(p0: Request?, p1: ApiError?, p2: UfileErrorBean?) {
//                }
//            })
        //文件列表
//        objectApiBuilder.objectList("deti")
//            .withMarker("")
//            .executeAsync(object : UfileCallback<ObjectListBean>(){
//                override fun onResponse(data: ObjectListBean?) {
//                    var sb = StringBuilder()
//                    data?.objectList?.forEach {
//                        sb.append("文件：${it.fileName} \n")
//                    }
//                    LogUtil.d("文件列表2：$data")
//                }
//
//                override fun onError(p0: Request?, p1: ApiError?, p2: UfileErrorBean?) {
//                }
//
//            })

    }
}