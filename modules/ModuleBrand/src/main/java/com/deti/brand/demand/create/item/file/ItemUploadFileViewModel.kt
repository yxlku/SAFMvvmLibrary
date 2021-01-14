package com.deti.brand.demand.create.item.file

import android.R.attr.data
import android.text.TextUtils
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.demand.create.item.pic.GlideEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.safmvvm.app.BaseApp
import com.safmvvm.ext.ui.file.AppFileSelector
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.zlylib.fileselectorlib.FileSelector
import com.zlylib.fileselectorlib.bean.EssFile
import com.zlylib.fileselectorlib.utils.Const


class ItemUploadFileViewModel(
    var mActivity: AppCompatActivity?,
    var mAdapter: BaseBinderAdapter,
): BaseViewModel<BaseModel>(BaseApp.getInstance()) {

    fun clickChooseFile(view: View, entity: ItemUploadFileEntity){
        mActivity?.apply {

            /**
             *  设置 onlyShowFolder() 只显示文件夹 后 再设置setFileTypes（）不生效
             *  设置 onlyShowFolder() 只显示文件夹 后 默认设置了onlySelectFolder（）
             *  设置 onlySelectFolder() 只能选择文件夹 后 默认设置了isSingle（）
             *  设置 isSingle() 只能选择一个 后 再设置了setMaxCount（） 不生效
             *
             */
            AppFileSelector.from(this)
                // .onlyShowFolder()  //只显示文件夹
                //.onlySelectFolder()  //只能选择文件夹
                 .isSingle() // 只能选择一个
                .setMaxCount(1) //设置最大选择数
                .setFileTypes("png", "doc", "apk", "mp3", "gif", "txt", "mp4", "zip") //设置文件类型
                .setSortType(FileSelector.BY_NAME_ASC) //设置名字排序
                .start(object : ActivityResultCallback<ActivityResult> {
                    override fun onActivityResult(result: ActivityResult?) {
                        result?.apply {
                            val fileList = data?.getParcelableArrayListExtra<EssFile>(Const.EXTRA_RESULT_SELECTION)
                            fileList?.apply {
                                val builder = StringBuilder()
                                forEach {
                                    builder.append(it.absolutePath).append("\n")
                                }
                                entity.filePath = builder.toString()
                                mAdapter.notifyDataSetChanged()
                                LogUtil.d("文件：${builder.toString()}")
                            }
                        }
                    }
                })
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