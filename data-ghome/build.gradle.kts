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

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.commonCore)
        }
    }
}


dependencies {
    implementation(libs.play.services.home)
    implementation(libs.play.services.home.types)
    implementation(projects.commonCore)
}

android {
    defaultConfig {
        buildConfigField("String", "GOOGLE_CLOUD_PROJECT_ID", projectNumber)
    }
}