pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GitHoob"

//  Platform
include(
    ":app"
)

//  Core
include(
    ":core:model",
    ":core:network",
    ":core:datastore",
    ":core:data",
    ":core:common"
)

//  Feature
include(
    ":feature:splash"
)