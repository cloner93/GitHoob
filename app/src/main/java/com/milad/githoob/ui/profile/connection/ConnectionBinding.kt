package com.milad.githoob.ui.profile.connection

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.R
import com.milad.model.User
import com.milad.common.mixTwoColors

@BindingAdapter("connection_items")
fun setConnectionItems(
    recyclerView: RecyclerView,
    items: List<User>?
) {
    items?.let {
        (recyclerView.adapter as ConnectionsRecyclerviewAdapter).submitList(items)
    }
}

@SuppressLint("ResourceType")
@BindingAdapter("set_color")
fun setColor(
    view: View,
    percent: Float
) {

    // color should be dynamic
    view.context.mixTwoColors(
        com.google.android.material.R.color.design_default_color_primary,
        com.google.android.material.R.color.design_default_color_surface,
        percent
    ).apply {
        view.setBackgroundColor(this)
    }
}