import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.library")
    alias(libs.plugins.kotlin.serialization)
}


kotlin{
    sourceSets{
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}