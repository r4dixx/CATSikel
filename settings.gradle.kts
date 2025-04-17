pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CATS_Test_SIKEL"

include(":app")
include(":core:ui")
include(":core:utils")
include(":data:persistence")
include(":data:remote")
include(":design")
include(":domain")
include(":feature:banks")
include(":feature:account")
