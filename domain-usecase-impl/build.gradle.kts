import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.commonCore)
            implementation(projects.domainCore)
            implementation(projects.domainRepository)
            implementation(projects.domainUsecase)
        }
    }
}