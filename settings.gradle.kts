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

includeBuild("build-logic")

include(":app")
include(":core:ui")
include(":core:utils")
include(":data:local")
include(":data:api")
include(":data:repository")
include(":design")
include(":domain")
include(":feature:banks")
include(":feature:account")
