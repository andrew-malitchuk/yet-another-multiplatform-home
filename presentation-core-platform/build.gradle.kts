import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    id("dev.yamh.io.convention.feature")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.material3.windowsizeclass.multiplatform)

            implementation(libs.kotlinx.serialization.core) // Use the latest version
            implementation(projects.presentationCoreNavigation)
            implementation(projects.presentationCoreStyling)
            implementation(projects.dataGhome)
        }
    }

}

dependencies{
    implementation("androidx.window:window:1.5.0")
//    implementation("androidx.compose.material3:material3-window-size-class:1.4.0")
}