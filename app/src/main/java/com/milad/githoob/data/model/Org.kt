package com.milad.githoob.data.model

data class Org(
    val id: Int,
    val login: String,
    val nodeId: String,
    val url: String,
    val reposUrl: String,
    val eventsUrl: String,
    val hooksUrl: String,
    val issuesUrl: String,
    val membersUrl: String,
    val publicMembersUrl: String,
    val avatarUrl: String,
    val description: String
)