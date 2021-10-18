package com.milad.githoob.data.model

import java.util.*

data class User(
    var login: String = "",
    var id: Long = 1,
    var node_id: String = "",
    var avatar_url: String = "",
    var gravatar_id: String = "",
    var url: String = "",
    var html_url: String = "",
    var followers_url: String = "",
    var following_url: String = "",
    var gists_url: String = "",
    var starred_url: String = "",
    var subscriptions_url: String = "",
    var organizations_url: String = "",
    var repos_url: String = "",
    var events_url: String = "",
    var received_events_url: String = "",
    var type: String = "",
    var site_admin: Boolean = false,
    var name: String = "",
    var company: String = "",
    var blog: String = "",
    var location: String = "",
    var email: String = "",
    var hireable: Boolean = false,
    var bio: String = "",
    var public_repos: Long = 1,
    var public_gists: Long = 1,
    var followers: Long = 1,
    var following: Long = 1,
    var created_at: Date? = null,
    var updated_at: Date? = null
)