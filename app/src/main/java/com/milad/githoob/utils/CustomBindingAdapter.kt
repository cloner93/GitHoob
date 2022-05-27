package com.milad.githoob.utils

import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.milad.githoob.R
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("profileImage")
fun profileImage(
    view: ImageView,
    imageUrl: String?
) {
    Glide.with(view.context)
        .load(imageUrl)
        .thumbnail(0.5f)
        .circleCrop()
        .into(view)
}

@BindingAdapter("profile_createdAt")
fun profileCreatedAt(
    tvCreateAt: TextView,
    created_at: String?
) {

    if (created_at != null) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")

        try {
            val time: Long = sdf.parse(created_at).time
            val now = System.currentTimeMillis()
            val ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)

            tvCreateAt.text = "joined: $ago"
        } catch (e: ParseException) {
            e.printStackTrace()
            tvCreateAt.visibility = View.GONE
        }
    }

}