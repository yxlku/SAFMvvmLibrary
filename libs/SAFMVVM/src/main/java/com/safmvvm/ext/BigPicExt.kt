package com.safmvvm.ext

import com.hitomi.tilibrary.style.index.NumberIndexIndicator
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator
import com.hitomi.tilibrary.transfer.TransferConfig
import com.safmvvm.R
import com.safmvvm.app.BaseApp
import com.safmvvm.app.globalconfig.GlobalConfig
import com.vansz.glideimageloader.GlideImageLoader


/**
 * 配置拓展
 */
fun configBigPicBuilder(
	configExpand: (builder: TransferConfig.Builder) -> Unit = {}
): TransferConfig.Builder{
	return TransferConfig.build()
		.setProgressIndicator(ProgressBarIndicator())
		.setIndexIndicator(NumberIndexIndicator())
		.enableDragPause(true)
		.enableJustLoadHitPage(true)
		//资源加载占位图
		.setMissPlaceHolder(GlobalConfig.ImageView.gImageResPlaceholder ?: R.drawable.ic_empty_photo)
		//错误图
		.setErrorPlaceHolder(GlobalConfig.ImageView.gImageResError ?: R.drawable.ic_empty_photo)
		//资源加载进度指示器, 可以实现 IProgressIndicator 扩展
		.setProgressIndicator(GlobalConfig.ImageView.gBigPicProgress ?: ProgressPieIndicator())
		//指示器
		.setIndexIndicator(GlobalConfig.ImageView.gBigPicIndexIndicator ?: NumberIndexIndicator())
		//图片加载器
		.setImageLoader(GlobalConfig.ImageView.gBigPicImageLoad ?: GlideImageLoader.with(BaseApp.getInstance())).apply {
			configExpand(this)
		}
}