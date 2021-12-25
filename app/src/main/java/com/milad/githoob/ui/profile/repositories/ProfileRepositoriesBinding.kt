package com.milad.githoob.ui.profile.repositories

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.milad.githoob.R
import com.milad.githoob.data.model.event.Repo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("repositories_items")
fun setRepoItems(
    recyclerView: RecyclerView,
    items: List<Repo>?
) {
    items?.let {
        (recyclerView.adapter as ProfileRepositoryAdapter).submitList(items)
    }
}

@SuppressLint("ResourceType")
@BindingAdapter("repositories_item_chip")
fun setItemChip(
    chipGroup: ChipGroup,
    items: List<String>?
) {
    items?.let {
        for (topic in items) {
            val chipChild: Chip = Chip(chipGroup.context).apply {
                text = topic
            }

            chipGroup.addView(chipChild)
        }
    }
}

@BindingAdapter("repositories_item_updateTime")
fun setRepoUpdateTimeText(
    tvTime: TextView,
    created_at: String
) {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    sdf.timeZone = TimeZone.getTimeZone("GMT")

    try {
        val time: Long = sdf.parse(created_at).time
        val now = System.currentTimeMillis()
        val ago =
            DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)

        tvTime.text = ago
    } catch (e: ParseException) {
        e.printStackTrace()
        tvTime.text = "N/A"
    }
}

@BindingAdapter("repositories_item_langColor")
fun setRepositoriesItemLangColor(
    linearLayout: LinearLayout,
    color: Int
) {
    color.let {
        linearLayout.setBackgroundColor(color)
    }
}