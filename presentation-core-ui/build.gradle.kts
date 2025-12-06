plugins {
    id("dev.yamh.io.convention.feature")
    id("dev.yamh.io.convention.di")
    alias(libs.plugins.kotlin.serialization)
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.material3.windowsizeclass.multiplatform)
            implementation(compose.material3)
            implementation(libs.stately.collections)
            implementation(libs.atomicfu)

            implementation(libs.kotlinx.serialization.core) // Use the latest version
            implementation(projects.commonCore)
            implementation(projects.presentationCoreNavigation)
            implementation(projects.presentationCoreStyling)
            implementation(projects.dataGhome)
            implementation(projects.presentationCorePlatform)
            implementation(projects.presentationCoreLocalisation)
        }
    }
}

