import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
}

val secretsProperties = Properties().apply {
    load(File(project.rootDir, "configure/secrets.properties").inputStream())
}

val projectNumber = secretsProperties["PROJECT_NUMBER"] as String

dependencies {
    // Home API SDK dependency:
    implementation("com.google.android.gms:play-services-home:17.0.0")
    implementation("com.google.android.gms:play-services-home-types:17.0.0")
}


android {
    defaultConfig {
        buildConfigField("String", "GOOGLE_CLOUD_PROJECT_ID", projectNumber)
    }
}