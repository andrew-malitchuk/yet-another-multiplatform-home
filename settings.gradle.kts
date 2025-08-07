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

include(":composeApp")
include(":data-ghome")
include(":presentation-core-ui")
include(":presentation-core-navigation")
include(":presentation-feature-homes")
include(":presentation-feature-rooms")