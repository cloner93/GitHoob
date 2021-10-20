package com.milad.githoob.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter("profileImage")
    fun profileImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("profileImageBlur")
    fun profileImageBlur(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25)))
            .into(view)
    }
}