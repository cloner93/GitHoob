package com.milad.githoob.ui.profile.organization

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.Org

@BindingAdapter("organisation_items")
fun setOrgsItems(
    recyclerView: RecyclerView,
    items: List<Org>?
) {
    items?.let {
        (recyclerView.adapter as ProfileOrgsAdapter).submitList(items)
    }
}