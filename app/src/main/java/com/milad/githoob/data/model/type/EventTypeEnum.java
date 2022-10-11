package com.milad.githoob.data.model.type;


import android.annotation.SuppressLint;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.milad.githoob.R;

public enum EventType {
    WatchEvent(R.string.starred, R.drawable.ic_star),
    CreateEvent(R.string.created_repo, R.drawable.ic_repo),
    ForkEvent(R.string.forked2, R.drawable.ic_fork),
    IssueCommentEvent(R.string.commented_on_issue, R.drawable.ic_comment),
    IssuesEvent(R.string.created_issue, R.drawable.ic_issues),
    MemberEvent(R.string.member, R.drawable.ic_add),
    PublicEvent(R.string.public_event, R.drawable.ic_repo),
    PullRequestEvent(R.string.pull_request, R.drawable.ic_pull_requests),
    PullRequestReviewCommentEvent(R.string.pr_comment_review, R.drawable.ic_comment),
    PushEvent(R.string.pushed, R.drawable.ic_push),
    DeleteEvent(R.string.deleted, R.drawable.ic_trash),
    ReleaseEvent(R.string.released, R.drawable.ic_download),
    UnknownEvent(R.string.unknown, R.drawable.ic_issues),
    CommitCommentEvent(R.string.commented_on_commit, R.drawable.ic_comment),
    DownloadEvent(R.string.downloaded, R.drawable.ic_download),
    FollowEvent(R.string.followed, R.drawable.ic_add),
    GistEvent(R.string.created_gist, R.drawable.ic_gists),
    GollumEvent(R.string.gollum, R.drawable.ic_info_outline),
    PullRequestReviewEvent(R.string.pr_review_event, R.drawable.ic_eye),
    RepositoryEvent(R.string.repo_event, R.drawable.ic_repo),
    TeamAddEvent(R.string.team_event, R.drawable.ic_profile),
    ForkApplyEvent(R.string.forked, R.drawable.ic_fork),
    OrgBlockEvent(R.string.organization_event, R.drawable.ic_profile),
    ProjectCardEvent(R.string.card_event, R.drawable.ic_info_outline),
    ProjectColumnEvent(R.string.project_event, R.drawable.ic_info_outline),
    OrganizationEvent(R.string.organization_event, R.drawable.ic_profile),
    ProjectEvent(R.string.project_event, R.drawable.ic_info_outline);

    @StringRes
    int type;
    @DrawableRes
    int drawableRes;


    EventType(@StringRes int type, @DrawableRes int drawableRes) {
        this.type = type;
        this.drawableRes = drawableRes;
    }

    @SuppressLint("ResourceType")
    @DrawableRes
    public int getDrawableRes() {
        return drawableRes > 0 ? drawableRes : R.drawable.ic_info_outline;
    }

    @SuppressLint("ResourceType")
    @StringRes
    public int getType() {
        return type > 0 ? type : R.string.unknown;
    }
}