package com.milad.githoob.ui.profile.overview

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Events
import com.milad.githoob.ui.profile.ProfileOverviewAdapter
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.GitHubContributionsView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("eventTimeText")
fun setEventTimeText(
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

@BindingAdapter(value = ["tvDesc", "events"], requireAll = false)
fun setItemTitle(
    tvTitle: TextView,
    tvDesc: TextView,
    events: Events
) {

    when (events.type) {
        "WatchEvent" -> {
            val s = SpannableStringBuilder()
                .append("Starred ")
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.visibility = View.GONE
        }
        "IssueCommentEvent" -> {
            val s = SpannableStringBuilder()
                .append(
                    events.payload.action.capitalize() +
                            " comment on issue " +
                            "${events.payload.issue.number} in "
                )
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.text = events.payload.comment.body
        }
        "PullRequestReviewCommentEvent" -> {

            val s = SpannableStringBuilder()
                .append(
                    "${events.payload.action.capitalize()}" +
                            " pull request review comment at "
                )
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.text = events.payload.comment.body

        }
        "CreateEvent" -> {

            val s = SpannableStringBuilder()
                .append("Created ${events.payload.ref_type} ")
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.text = events.payload.description

        }
        "PullRequestEvent" -> {

            val s = SpannableStringBuilder()
                .append(
                    "${events.payload.action.capitalize()} pull request " +
                            "${events.payload.pull_request.number} in "
                )
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.text =
                "${events.payload.pull_request.title}\n" +
                        "${events.payload.pull_request.body}"

        }
        "PushEvent" -> {

            val s = SpannableStringBuilder()
                .append(
                    "Pushed to " +
                            "${events.payload.ref.substringAfterLast("/")} in "
                )
                .bold { append(events.repo.name) }

            tvTitle.text = s

            val d = SpannableStringBuilder()

            for (i in events.payload.commits) {
                d.color(Color.BLUE) { append("\n${i.sha.substring(0, 7)} ") }
                    .append("${i.message}")
            }

            tvDesc.text = d

        }
        "DeleteEvent" -> {

            val s = SpannableStringBuilder()
                .append("Delete ${events.payload.ref_type} ")
                .bold { append(events.payload.ref) }

            tvTitle.text = s
            tvDesc.visibility = View.GONE

        }
        "ForkEvent" -> {

            val s = SpannableStringBuilder()
                .append("Forked ")
                .bold { append("${events.repo.name} ") }
                .append("to ")
                .bold { append(events.payload.forkee.full_name) }

            tvTitle.text = s
            tvDesc.visibility = View.GONE

        }
        "IssuesEvent" -> {

            val s = SpannableStringBuilder()
                .append(
                    "${events.payload.action.capitalize()} issue " +
                            "${events.payload.issue.number} in "
                )
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.text = "${events.payload.issue.title}\n" +
                    "${events.payload.issue.body}"

        }
        "PublicEvent" -> {

            val s = SpannableStringBuilder()
                .append("Made ")
                .bold { append(events.repo.name) }
                .append(" public.")

            tvTitle.text = s
            tvDesc.visibility = View.GONE

        }
        "MemberEvent" -> {

            val s = SpannableStringBuilder()
                .append("Added Member ")
                .bold { append(events.payload.member.login) }
                .append(" to ")
                .bold { append(events.repo.name) }

            tvTitle.text = s
            tvDesc.visibility = View.GONE

        }
        "ReleaseEvent" -> {

            try {

                val s = SpannableStringBuilder()
                if (events.payload.release.tag_name != null) {
                    s.append(
                        "${events.payload.action.capitalize()} release " +
                                "${events.payload.release.tag_name} at "
                    )
                        .bold { append(events.repo.name) }
                } else if (events.payload.release.name != null) {
                    s.append(
                        "${events.payload.action.capitalize()} release " +
                                "${events.payload.release.name} at "
                    )
                        .bold { append(events.repo.name) }
                } else {
                    s.append(
                        "${events.payload.action.capitalize()} release at "
                    )
                        .bold { append(events.repo.name) }
                }
                tvTitle.text = s
            } catch (e: Exception) {
                val s = SpannableStringBuilder()
                    .append("${events.payload.action.capitalize()} at")
                    .bold { append(events.repo.name) }

                tvTitle.text = s
            }
            tvDesc.visibility = View.GONE

        }
        else -> {

            val s = SpannableStringBuilder()
                .bold { color(Color.RED) { append("Type ${events.type}") } }

            tvTitle.text = s
            tvDesc.visibility = View.GONE
        }
    }

}


@BindingAdapter("overview_items")
fun setItems(
    recyclerView: RecyclerView,
    items: List<Events>?
) {
    items?.let {
        (recyclerView.adapter as ProfileOverviewAdapter).submitList(items)
    }
}

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