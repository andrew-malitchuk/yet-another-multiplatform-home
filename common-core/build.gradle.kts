import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    kotlin("plugin.serialization") version "2.0.20"
}


kotlin{
    sourceSets{
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}