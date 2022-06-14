package com.milad.githoob.ui.profile.connection

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.R
import com.milad.githoob.data.model.User
import com.milad.githoob.utils.mixTwoColors

@BindingAdapter("connection_items")
fun setConnectionItems(
    recyclerView: RecyclerView,
    items: List<User>?
) {
    items?.let {
        (recyclerView.adapter as ConnectionsRecyclerviewAdapter).submitList(items)
    }
}

@BindingAdapter("set_color")
fun setColor(
    view: View,
    percent: Float
) {
    view.context.mixTwoColors(
        R.attr.colorPrimary,
        R.attr.colorSurface,
        percent
    ).apply {
        view.setBackgroundColor(this)
    }
}