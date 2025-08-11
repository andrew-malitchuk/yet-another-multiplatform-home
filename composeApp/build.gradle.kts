import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.application")
    id("dev.yamh.io.convention.di")
    // TODO: fix
    kotlin("plugin.serialization") version "2.0.20"
}

kotlin {
    sourceSets {
        commonMain {
            commonMain {
                dependencies {
                    implementation(libs.navigation.compose)

                    implementation(projects.dataGhome)
                    implementation(projects.presentationCoreNavigation)
                    implementation(projects.presentationFeatureHomes)
                    implementation(projects.presentationFeatureRooms)
                }
            }
        }
    }
}

dependencies {
//    debugImplementation(compose.uiTooling)
    // Home API SDK dependency:
    implementation("com.google.android.gms:play-services-home:17.0.0")
    implementation("com.google.android.gms:play-services-home-types:17.0.0")

}

