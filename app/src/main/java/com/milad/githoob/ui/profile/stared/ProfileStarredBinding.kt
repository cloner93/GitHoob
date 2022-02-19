package com.milad.githoob.ui.profile.stared

import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Repo
import timber.log.Timber

@BindingAdapter("starred_items")
fun setStarredItems(
    recyclerView: RecyclerView,
    items: List<Repo>?
) {
    items?.let {
        (recyclerView.adapter as ProfileStaredAdapter).submitList(items)
    }
}