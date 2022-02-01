package com.milad.githoob.utils

import android.net.Uri
import androidx.core.net.toUri

object InternalDeepLink {
    private const val DOMAIN = "githoob://"

    const val PROFILE = "${DOMAIN}profile"
    const val REPOSITORY = "${DOMAIN}repository"

    fun makeProfileAuthenticatedDeepLink(token: String = ""): Uri {
        return "${DOMAIN}profile/?token=${token}".toUri()
    }

    fun makeProfileDeepLink(userId: String = ""): Uri {
        return "${DOMAIN}profile/?userId=${userId}".toUri()
    }
}