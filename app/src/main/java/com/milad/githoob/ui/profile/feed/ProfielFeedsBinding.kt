package com.milad.githoob.ui.profile.feed

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Events

@BindingAdapter("feeds_items")
fun setFeedsItems(
    recyclerView: RecyclerView,
    items: List<Events>?
) {
    items?.let {
        (recyclerView.adapter as ProfileFeedsAdapter).submitList(items)
    }
}