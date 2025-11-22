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
    sourceSets{
        commonMain.dependencies {
            api(compose.components.resources)
            implementation(projects.presentationCoreLocalisation)
        }
    }
}

