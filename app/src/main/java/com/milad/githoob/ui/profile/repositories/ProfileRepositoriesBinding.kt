package com.milad.githoob.ui.profile.repositories

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Repo

@BindingAdapter("repositories_items")
fun setItems(
    recyclerView: RecyclerView,
    items: List<Repo>?
) {
    items?.let {
        (recyclerView.adapter as ProfileRepositoryAdapter).submitList(items)
    }
}
