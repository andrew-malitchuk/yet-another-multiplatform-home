import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
}


dependencies {
    // Home API SDK dependency:
    implementation("com.google.android.gms:play-services-home:17.0.0")
    implementation("com.google.android.gms:play-services-home-types:17.0.0")
}

