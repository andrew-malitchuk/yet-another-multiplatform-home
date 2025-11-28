plugins {
    id("dev.yamh.io.convention.feature")
    id("dev.yamh.io.convention.di")
    alias(libs.plugins.kotlin.serialization)
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.core) // Use the latest version
            implementation(projects.domainCore)
            implementation(projects.domainUsecase)
            implementation(projects.presentationCoreStyling)
            implementation(projects.presentationCoreUi)
            implementation(projects.presentationCoreLocalisation)
            implementation(projects.presentationCorePlatform)
            implementation(projects.presentationCoreNavigation)
        }
    }
}

