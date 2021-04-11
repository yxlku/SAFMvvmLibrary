package com.safmvvm.ext

import com.hitomi.tilibrary.style.index.NumberIndexIndicator
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator
import com.hitomi.tilibrary.transfer.TransferConfig
import com.safmvvm.app.BaseApp
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
		.setImageLoader(GlideImageLoader.with(BaseApp.getInstance())).apply {
			configExpand(this)
		}
}