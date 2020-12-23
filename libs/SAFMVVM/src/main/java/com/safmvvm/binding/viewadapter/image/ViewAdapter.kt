package com.safmvvm.binding.viewadapter.image

import android.text.TextUtils
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.safmvvm.app.globalconfig.GlobalConfig


/**
 * 加载网络图片
 */
@BindingAdapter(value = ["url", "placeholderRes", "errorRes"], requireAll = false)
fun setImageUri(
    imageView: ImageView,
    url: String?,
    @DrawableRes placeholder: Int?,
    @DrawableRes error: Int?
) {
    if (!TextUtils.isEmpty(url)) {
        //使用Glide框架加载图片
        val request = Glide.with(imageView.context)
            .load(url)
        val options = RequestOptions()

        if (placeholder != null) {
            options.placeholder(placeholder)
        } else {
            val placeholderRes = GlobalConfig.ImageView.gImageResPlaceholder
            placeholderRes?.let { options.placeholder(placeholderRes) }
        }
        if (error != null) {
            options.error(error)
        } else {
            val errorRes = GlobalConfig.ImageView.gImageResError
            errorRes?.let { options.error(errorRes) }
        }
        request.apply(options).into(imageView)
    } else {
        imageView.setImageResource(0)
    }
}