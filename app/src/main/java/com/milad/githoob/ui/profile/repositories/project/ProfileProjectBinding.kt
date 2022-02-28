package com.milad.githoob.ui.profile.repositories.project

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Contributor
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.ui.profile.repositories.ProfileProjectContributorsAdapter

@BindingAdapter("project_contributors_items")
fun setContributorsItems(
    recyclerView: RecyclerView,
    items: List<Contributor>?
) {
    items?.let {
        (recyclerView.adapter as ProfileProjectContributorsAdapter).submitList(items)
    }
}