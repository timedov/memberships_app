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

rootProject.name = "ForBoost"
include(":app")
include(":core")
include(":core:common")
include(":core:domain")
include(":features")
include(":features:auth")
include(":core:data")
include("core:data:firebase")
include(":core:data:network")
include(":core:ui")
