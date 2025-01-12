package com.milad.githoob.ui.profile.stared

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.model.event.Repo

@BindingAdapter("starred_items")
fun setStarredItems(
    recyclerView: RecyclerView,
    items: List<Repo>?
) {
    items?.let {
        (recyclerView.adapter as ProfileStaredAdapter).submitList(items)
    }
}