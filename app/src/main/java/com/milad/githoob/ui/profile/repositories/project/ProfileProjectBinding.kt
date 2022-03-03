package com.milad.githoob.ui.profile.repositories.project

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Contributor
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.ImagesPlugin

@BindingAdapter("project_contributors_items")
fun setContributorsItems(
    recyclerView: RecyclerView,
    items: List<Contributor>?
) {
    items?.let {
        (recyclerView.adapter as ProfileProjectContributorsAdapter).submitList(items)
    }
}

@BindingAdapter("set_readme")
fun setReadMe(
    textView: TextView,
    md: String
) {
    val markdown = Markwon.builder(textView.context)
        .usePlugin(ImagesPlugin.create())
        .usePlugin(HtmlPlugin.create())
        .usePlugin(IFrameHtmlPlugin())
        .build();

    markdown.setMarkdown(textView, md);
}










