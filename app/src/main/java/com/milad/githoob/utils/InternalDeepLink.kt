package com.milad.githoob.utils

import android.net.Uri
import androidx.core.net.toUri

object InternalDeepLink {
    private const val DOMAIN = "githoob://"

    const val PROFILE = "${DOMAIN}profile"
    const val REPOSITORY = "${DOMAIN}repository"

    fun makeProfileDeepLink(userId: String?, token: String?) =
        "${DOMAIN}profile/?userId=$userId&token=${token}".toUri()

    fun makeRepositoryUserDeepLink(userId: String?, token: String?) =
        "${DOMAIN}repository/?userId=$userId&token=${token}".toUri()

    fun makeStarredUserDeepLink(userId: String?, token: String?) =
        "${DOMAIN}starred/?userId=$userId&token=${token}".toUri()

    fun makeOrganizationDeepLink(userId: String?, token: String?) =
        "${DOMAIN}orgs/?userId=$userId&token=${token}".toUri()
}