package com.test.common.ui.dialog.pic

import android.app.Activity
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.app.BaseApp

fun createDialogPhotoSelect(
    activity: Activity,
    /** 最多选几个*/
    maxSelectCount: Int = 1,
    /** 拍照*/
    takePhotoClick: () -> Unit = {},
    /** 相册*/
    photoAlbumClick: () -> Unit = {},
    /** 点击图片*/
    photoSelectBlock: (data: PhotoSelectEntity, position: Int, view: View, popupView: BasePopupView) -> Unit = { data: PhotoSelectEntity, position: Int, view: View, popupView: BasePopupView->}
): BasePopupView = XPopup.Builder(BaseApp.getInstance()).apply {
    //如果不加这个，评论弹窗会移动到软键盘上面
    moveUpToKeyboard(false)
    //允许拖拽
    enableDrag(true)
    //对于只使用一次的弹窗，推荐设置这个
    isDestroyOnDismiss(false)
}.asCustom(PhotoSelectPopupView(activity, maxSelectCount, takePhotoClick, photoAlbumClick, photoSelectBlock))



