import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.dataPreference)
            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.coroutines)
        }
    }
}