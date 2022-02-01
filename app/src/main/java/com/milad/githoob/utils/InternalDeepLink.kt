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
}