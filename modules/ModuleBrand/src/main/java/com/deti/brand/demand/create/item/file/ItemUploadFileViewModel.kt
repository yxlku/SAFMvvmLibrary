package com.deti.brand.demand.create.item.file

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.safmvvm.app.BaseApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ext.toFileBlock


class ItemUploadFileViewModel(
    var mActivity: AppCompatActivity?,
    var mAdapter: BaseBinderAdapter,
): BaseViewModel<BaseModel>(BaseApp.getInstance()) {

    fun clickChooseFile(view: View, entity: ItemUploadFileEntity){
        mActivity?.apply {
            toFileBlock(this, "选择文件"){ activityResult: ActivityResult, intent: Intent?, resultCode: Int, filePath: String? ->
                LogUtil.d("文件地址: $filePath")
                filePath?.apply {
                    entity.filePath = this
                    mAdapter.notifyDataSetChanged()
                }?: run{
                    ToastUtil.showShortToast("文件选择错误")
                }
            }
        }
    }

    fun takePhoto(entity: ItemUploadFileEntity){
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
                            entity.filePath = path
                            mAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onCancel() {
                        ToastUtil.showShortToast("取消")
                    }

                })
        }
    }

}