package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.text.TextUtils
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.demand.create.item.file.ItemUploadFileEntity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.safmvvm.app.BaseApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ui.dialog.pic.createDialogPhotoSelect

class ItemPicChooseItemViewModel(
    var mActivity: Activity?,
    var mAdapter: ItemPicChooseItemAdapter,
) : BaseViewModel<BaseModel>(BaseApp.getInstance()) {
    fun clickDel(v: View, entity: ItemPicChooseItemEntity) {
        entity.picPath = ""
        mAdapter.notifyDataSetChanged()
    }



}