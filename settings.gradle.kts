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
include(":core:data:api")
include(":core:data:impl")
include(":core:data:impl:firebase")
include(":core:data:impl:local")
include(":core:ui")
include(":features")
include(":features:signin")
include(":features:profile")
include(":features:feed")
include(":features:postdetails")
include(":features:savepost")
include(":features:commentreplies")
include(":features:uploadpost")
include(":features:feed:api")
include(":features:feed:impl")
include(":features:signin:api")
include(":features:signin:impl")
include(":features:commentreplies:api")
include(":features:commentreplies:impl")
include(":features:postdetails:api")
include(":features:postdetails:impl")
include(":features:profile:api")
include(":features:profile:impl")
include(":features:savepost:api")
include(":features:savepost:impl")
include(":features:uploadpost:api")
include(":features:uploadpost:impl")
