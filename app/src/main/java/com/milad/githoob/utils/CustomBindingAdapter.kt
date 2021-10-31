package com.milad.githoob.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.GitHubContributionsView

object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter("profileImage")
    fun profileImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("contributeGraphData")
    fun contributeGraphData(
        gitHubContributionsView: GitHubContributionsView,
        contributions: List<ContributionsDay>?
    ) {
        if (contributions != null) {
            val filter: List<ContributionsDay> =
                gitHubContributionsView.getLastContributions(contributions)

            val bitmap = gitHubContributionsView.drawOnCanvas(filter, contributions)
            if (bitmap != null)
                gitHubContributionsView.onResponse()
        }

    }
}