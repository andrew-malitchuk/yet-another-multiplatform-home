import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("dev.yamh.io.convention.application")
    id("dev.yamh.io.convention.di")
        alias(libs.plugins.kotlin.serialization)
}

android {
    sourceSets["main"].res.srcDirs("src/androidMain/res")
}

kotlin {

    sourceSets {
        commonMain {
            commonMain {
                dependencies {
                    implementation(libs.navigation.compose)

                    implementation(projects.commonCore)
                    implementation(projects.dataGhome)
                    implementation(projects.dataPreference)
                    implementation(projects.dataPreferenceImpl)
                    implementation(projects.dataDatabase)
                    implementation(projects.dataDatabaseImpl)
                    implementation(projects.dataRepositoryImpl)
                    implementation(projects.domainCore)
                    implementation(projects.domainUsecase)
                    implementation(projects.domainUsecaseImpl)
                    implementation(projects.domainRepository)
                    implementation(projects.presentationCorePlatform)
                    implementation(projects.presentationCoreNavigation)
                    implementation(projects.presentationCoreStyling)
                    implementation(projects.presentationCoreUi)
                    implementation(projects.presentationFeatureSplash)
                    implementation(projects.presentationFeatureOnboarding)
                    implementation(projects.presentationFeatureAuthorization)
                    implementation(projects.presentationFeatureHomes)
                    implementation(projects.presentationFeatureMain)
                    implementation(projects.presentationFeatureDevice)
                    implementation(projects.presentationFeatureRoom)
                    implementation(projects.presentationFeatureSettings)
                }
            }
        }
    }
}

dependencies {
//    debugImplementation(compose.uiTooling)
    // Home API SDK dependency:
    implementation("com.google.android.gms:play-services-home:17.0.0")
    implementation("com.google.android.gms:play-services-home-types:17.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:1.6.4")

    implementation("androidx.glance:glance-appwidget:1.1.0")
    implementation("androidx.glance:glance-material3:1.1.0")

    implementation(libs.kotlinx.serialization.json)
}

