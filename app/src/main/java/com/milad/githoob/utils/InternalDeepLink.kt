package com.milad.githoob.utils

import androidx.core.net.toUri
import com.milad.githoob.ui.profile.connection.ConnectionType

object InternalDeepLink {
    private const val DOMAIN = "githoob://"

    fun makeProfileDeepLink(userId: String = "", token: String = "") =
        "${DOMAIN}profile/?userId=$userId&token=${token}".toUri()

    fun makeRepositoryUserDeepLink(userId: String = "", token: String = "") =
        "${DOMAIN}repository/?userId=$userId&token=${token}".toUri()

    fun makeStarredUserDeepLink(userId: String = "", token: String = "") =
        "${DOMAIN}starred/?userId=$userId&token=${token}".toUri()

    fun makeOrganizationDeepLink(userId: String = "", token: String = "") =
        "${DOMAIN}orgs/?userId=$userId&token=${token}".toUri()

    fun makeProjectDeepLink(userId: String = "", token: String = "", projectName: String) =
        ("${DOMAIN}project/" +
                "?userId=$userId" +
                "&token=${token}" +
                "&projectName=${projectName}")
            .toUri()

    fun makeConnectionDeepLink(
        userId: String = "",
        token: String = "",
        type: ConnectionType = ConnectionType.followers
    ) =
        ("${DOMAIN}connection/" +
                "?userId=${userId}" +
                "&token=${token}" +
                "&tab=${type.name}")
            .toUri()
}