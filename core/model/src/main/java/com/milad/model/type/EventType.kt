package com.milad.model.type

enum class EventType {
    WatchEvent, CreateEvent, ForkEvent, IssueCommentEvent, IssuesEvent, MemberEvent, PublicEvent, PullRequestEvent, PullRequestReviewCommentEvent, PushEvent, DeleteEvent, ReleaseEvent, UnknownEvent, CommitCommentEvent, DownloadEvent, FollowEvent, GistEvent, GollumEvent, PullRequestReviewEvent, RepositoryEvent, TeamAddEvent, ForkApplyEvent, OrgBlockEvent, ProjectCardEvent, ProjectColumnEvent, OrganizationEvent, ProjectEvent;

    var type = 0
    var drawableRes = 0

    constructor(type: Int, drawableRes: Int) {
        this.type = type
        this.drawableRes = drawableRes
    }

    constructor()
}