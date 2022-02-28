package com.milad.githoob.utils

import androidx.core.net.toUri

object InternalDeepLink {
    private const val DOMAIN = "githoob://"

    fun makeProfileDeepLink(userId: String?, token: String?) =
        "${DOMAIN}profile/?userId=$userId&token=${token}".toUri()

    fun makeRepositoryUserDeepLink(userId: String?, token: String?) =
        "${DOMAIN}repository/?userId=$userId&token=${token}".toUri()

    fun makeStarredUserDeepLink(userId: String?, token: String?) =
        "${DOMAIN}starred/?userId=$userId&token=${token}".toUri()

    fun makeOrganizationDeepLink(userId: String?, token: String?) =
        "${DOMAIN}orgs/?userId=$userId&token=${token}".toUri()

    fun makeProjectDeepLink(userId: String?, token: String?, projectName: String) =
        ("${DOMAIN}project/" +
                "?userId=$userId" +
                "&token=${token}" +
                "&projectName=${projectName}")
            .toUri()
}