package com.milad.githoob.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("profileImage")
fun profileImage(
    view: ImageView,
    imageUrl: String?
) {
    Glide.with(view.context)
        .load(imageUrl)
        .circleCrop()
        .into(view)
}