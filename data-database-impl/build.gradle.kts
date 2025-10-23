import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.dataDatabase)
            implementation(libs.couchbase.lite)
            implementation(libs.couchbase.lite.ktx)
        }
    }
}