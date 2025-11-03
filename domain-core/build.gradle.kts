plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.commonCore)
            implementation(projects.dataGhome)
            implementation(projects.dataDatabase)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}