rootProject.name = "yet-another-multiplatform-home"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        mavenLocal()
        google()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}

include(":application-compose")
include(":common-core")
include(":data-ghome")
include(":data-preference")
include(":data-preference-impl")
include(":data-database")
include(":data-database-impl")
include(":data-repository")
include(":data-repository-impl")
include(":domain-core")
include(":domain-repository")
include(":domain-usecase")
include(":domain-usecase-impl")
include(":presentation-core-platform")
include(":presentation-core-styling")
include(":presentation-core-ui")
include(":presentation-core-localisation")
include(":presentation-core-navigation")
include(":presentation-feature-authorization")
include(":presentation-feature-homes")
include(":presentation-feature-main")
include(":presentation-feature-onboarding")
include(":presentation-feature-room")
include(":presentation-feature-device")
include(":presentation-feature-settings")
include(":presentation-feature-splash")