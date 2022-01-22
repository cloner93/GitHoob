package com.milad.githoob.ui.profile.feed

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.core.text.bold
import androidx.core.text.color
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Event
import com.milad.githoob.data.model.type.EventType
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileFeedsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _eventsList = MutableLiveData<ArrayList<Event>>()
    val eventList: LiveData<ArrayList<Event>> = _eventsList.switchMap {
        switchMatData(it)
    }

    private fun switchMatData(list: ArrayList<Event>): LiveData<ArrayList<Event>> {
        val d = MutableLiveData<ArrayList<Event>>()

        list.map { events ->
            when (events.type) {
                "WatchEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Starred ")
                        .bold { append(events.repo?.name) }
                        .toString()

                    events.eventType = EventType.WatchEvent
                }
                "CreateEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Created ${events.payload.ref_type} ")
                        .bold { append(events.repo.name) }
                        .toString()
                    if (events.payload.description != null)
                        events.desc = events.payload.description

                    events.eventType = EventType.CreateEvent
                }
                "ForkEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Forked ")
                        .bold { append("${events.repo.name} ") }
                        .append("to ")
                        .bold { append(events.payload.forkee.full_name) }
                        .toString()
                    events.eventType = EventType.ForkEvent
                }
                "IssueCommentEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append(events.payload.action.capitalize() + " Comment on issue " + "${events.payload.issue.number} in ")
                        .bold { append(events.repo.name) }
                        .toString()
                    if (events.payload.comment.body != null)
                        events.desc = events.payload.comment.body

                    events.eventType = EventType.IssueCommentEvent
                }
                "IssuesEvent" -> {

                    events.title = SpannableStringBuilder()
                        .append("${events.payload.action.capitalize()} issue " + "${events.payload.issue.number} in ")
                        .bold { append(events.repo.name) }
                        .toString()

                    events.desc = "${events.payload.issue.title}\n" + events.payload.issue.body
                    events.eventType = EventType.IssuesEvent
                }
                "MemberEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Added Member ")
                        .bold { append(events.payload.member.login) }
                        .append(" to ")
                        .bold { append(events.repo.name) }
                        .toString()
                    events.eventType = EventType.MemberEvent

                }
                "PublicEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Made ")
                        .bold { append(events.repo.name) }
                        .append(" public.")
                        .toString()
                    events.eventType = EventType.PublicEvent
                }
                "PullRequestEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("${events.payload.action.capitalize()} pull request " + "${events.payload.pull_request.number} in ")
                        .bold { append(events.repo.name) }
                        .toString()

                    events.desc =
                        "${events.payload.pull_request.title}\n" + events.payload.pull_request.body
                    events.eventType = EventType.PullRequestEvent
                }
                "PullRequestReviewCommentEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append(events.payload.action.capitalize() + " pull request review comment at ")
                        .bold { append(events.repo.name) }
                        .toString()

                    if (events.payload.comment.body != null)
                        events.desc = events.payload.comment.body

                    events.eventType = EventType.PullRequestReviewCommentEvent
                }
                "DeleteEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Delete ${events.payload.ref_type} ")
                        .bold { append(events.payload.ref) }
                        .toString()

                    events.eventType = EventType.DeleteEvent
                }
                "ReleaseEvent" -> {
                    try {
                        val s = SpannableStringBuilder()
                        if (events.payload.release.tag_name != null) {
                            s.append("${events.payload.action.capitalize()} release " + "${events.payload.release.tag_name} at ")
                                .bold { append(events.repo.name) }
                        } else if (events.payload.release.name != null) {
                            s.append("${events.payload.action.capitalize()} release " + "${events.payload.release.name} at ")
                                .bold { append(events.repo.name) }
                        } else {
                            s.append("${events.payload.action.capitalize()} release at ")
                                .bold { append(events.repo.name) }
                        }
                        events.title = s.toString()
                    } catch (e: Exception) {
                        val s = SpannableStringBuilder()
                            .append("${events.payload.action.capitalize()} at")
                            .bold { append(events.repo.name) }
                        events.title = s.toString()
                    }
                    events.eventType = EventType.ReleaseEvent
                }
                "PushEvent" -> {
                    events.title = SpannableStringBuilder()
                        .append("Pushed to " + "${events.payload.ref.substringAfterLast("/")} in ")
                        .bold { append(events.repo.name) }
                        .toString()

                    val commits = SpannableStringBuilder()
                    var count = 0
                    for (i in events.payload.commits) {
                        val fkf = count == 1

                        commits.color(Color.BLUE) {
                            if (fkf) {
                                append("\n")
                            }
                            append("${i.sha.substring(0, 7)} ")
                        }
                            .append(i.message)
                        count = 1
                    }

                    events.desc = commits.toString()
                    events.eventType = EventType.PushEvent
                }
                "CommitCommentEvent",
                "DownloadEvent",
                "FollowEvent",
                "GistEvent",
                "GollumEvent",
                "PullRequestReviewEvent",
                "RepositoryEvent",
                "TeamAddEvent",
                "ForkApplyEvent",
                "OrgBlockEvent",
                "ProjectCardEvent",
                "ProjectColumnEvent",
                "OrganizationEvent",
                "ProjectEvent" -> {
                    events.title = SpannableStringBuilder()
                        .bold { color(Color.RED) { append("Type ${events.type}") } }
                        .toString()
                    events.eventType = EventType.UnknownEvent
                }
                else -> {
                    events.title = SpannableStringBuilder()
                        .bold { color(Color.RED) { append("Type ${events.type}") } }
                        .toString()
                    events.eventType = EventType.UnknownEvent
                }
            }
        }

        d.value = list
        return d
    }

    fun setUser(token: String, user: User) {

        viewModelScope.launch(ioDispatcher) {
            mainRepository.getMyEvents(token, user.login, 1).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        _eventsList.postValue(it.data!!)
                    }
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        Timber.d(it.message.toString())
                    }
                }
            }
        }
    }
}