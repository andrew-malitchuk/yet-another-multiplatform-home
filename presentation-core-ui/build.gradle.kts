import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    id("dev.yamh.io.convention.feature")
    id("dev.yamh.io.convention.di")
    // TODO: fix
    kotlin("plugin.serialization") version "2.0.20"
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.material3.windowsizeclass.multiplatform)
            implementation(compose.material3)


            implementation(libs.kotlinx.serialization.core) // Use the latest version
            implementation(projects.presentationCoreNavigation)
            implementation(projects.presentationCoreStyling)
            implementation(projects.dataGhome)
        }
    }
}

