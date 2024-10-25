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
include(":core:data")
include(":core:data:local")
include(":core:data:firebase")
include(":core:data:network")
include(":core:domain")
include(":core:ui")
include(":features")
include(":features:auth")
include(":features:profile")
include(":features:feed")
include(":features:savetier")
include(":features:subscribe")
include(":features:postdetails")
include(":features:savepost")
include(":features:commentreplies")
include(":features:uploadpost")
