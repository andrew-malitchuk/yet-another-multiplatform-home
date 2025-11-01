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
            implementation(projects.dataPreference)
            implementation(projects.domainCore)
            implementation(projects.domainRepository)

            implementation(libs.kotlinx.serialization.json)
        }
    }
}