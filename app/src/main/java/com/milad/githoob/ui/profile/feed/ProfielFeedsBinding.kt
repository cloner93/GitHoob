package com.milad.githoob.ui.profile.feed

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Event

@BindingAdapter("feeds_items")
fun setFeedsItems(
    recyclerView: RecyclerView,
    items: List<Event>?
) {
    items?.let {
        (recyclerView.adapter as ProfileFeedsAdapter).submitList(items)
    }
}