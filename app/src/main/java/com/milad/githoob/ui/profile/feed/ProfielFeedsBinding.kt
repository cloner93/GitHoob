package com.milad.githoob.ui.profile.feed

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.model.event.Event

@BindingAdapter("feeds_items")
fun setFeedsItems(
    recyclerView: RecyclerView,
    items: List<Event>?
) {
    items?.let {
        (recyclerView.adapter as ProfileFeedsAdapter).submitList(items)
    }
}

@BindingAdapter("feed_item_icon")
fun setFeedItemIcon(imageView: ImageView, @DrawableRes drawableRes: Int) {

    val drawable = ContextCompat.getDrawable(imageView.context, drawableRes)
    imageView.setImageDrawable(drawable)

}