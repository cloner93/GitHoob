package com.milad.model.type


sealed class EventTypeSealed(
    type: Int = -1,
    drawableRes: Int = -1
) {
    object WatchEvent : EventTypeSealed() {
        fun fa(
            drawableRes: Int
        ): Int {
            return drawableRes
        }
    }

    object CreateEvent : EventTypeSealed()
    object ForkEvent : EventTypeSealed()
    object IssueCommentEvent : EventTypeSealed()
    object IssuesEvent : EventTypeSealed()
    object MemberEvent : EventTypeSealed()
    object PublicEvent : EventTypeSealed()
    object PullRequestEvent : EventTypeSealed()
    object PullRequestReviewCommentEvent : EventTypeSealed()
    object PushEvent : EventTypeSealed()
    object DeleteEvent : EventTypeSealed()
    object ReleaseEvent : EventTypeSealed()
    object UnknownEvent : EventTypeSealed()
    object CommitCommentEvent : EventTypeSealed()
    object DownloadEvent : EventTypeSealed()
    object FollowEvent : EventTypeSealed()
    object GistEvent : EventTypeSealed()
    object GollumEvent : EventTypeSealed()
    object PullRequestReviewEvent : EventTypeSealed()
    object RepositoryEvent : EventTypeSealed()
    object TeamAddEvent : EventTypeSealed()
    object ForkApplyEvent : EventTypeSealed()
    object OrgBlockEvent : EventTypeSealed()
    object ProjectCardEvent : EventTypeSealed()
    object ProjectColumnEvent : EventTypeSealed()
    object OrganizationEvent : EventTypeSealed()
    object ProjectEvent : EventTypeSealed()
}
