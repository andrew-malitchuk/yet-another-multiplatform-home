package dev.yamh.io.convention.source.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.utils.toImmutableSet
import dev.yamh.io.convention.core.ext.libs
import dev.yamh.io.convention.core.ext.moduleName
import dev.yamh.io.convention.source.plugin.base.BaseConventionPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.jvm.optionals.getOrNull

/**
 * Convention plugin for configuring Android and iOS application modules.
 *
 * This plugin applies required plugins, configures Android and iOS targets,
 * and sets up common dependencies for multiplatform Compose projects.
 */
class ApplicationConventionPlugin : BaseConventionPlugin() {

    /**
     * Applies necessary plugins for multiplatform, Android application, and Compose support.
     */
    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(project.libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        apply(project.libs.findPlugin("androidApplication").get().get().pluginId)
        apply(project.libs.findPlugin("composeMultiplatform").get().get().pluginId)
        apply(project.libs.findPlugin("composeCompiler").get().get().pluginId)
    }

    /**
     * Configures the Android platform for the application module.
     *
     * Sets up SDK versions, application ID, source sets, packaging options,
     * build types, and Java compatibility.
     */
    override fun Project.configureAndroidPlatform() {
        val minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        val targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
        val versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
        val versionName = libs.findVersion("versionName").get().requiredVersion
        val applicationId = libs.findVersion("applicationId").get().requiredVersion

        extensions.getByType<ApplicationExtension>().apply {
            namespace = applicationId
            compileSdk = targetSdk
            defaultConfig {
                this.minSdk = minSdk
                this.targetSdk = targetSdk
                this.versionCode = versionCode
                this.versionName = versionName
                this.applicationId = applicationId
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
            }
            sourceSets["main"].apply {
                manifest.srcFile("src/androidMain/AndroidManifest.xml")
                res.srcDirs("src/androidMain/res")
                resources.srcDirs("src/commonMain/resources")
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }
    }

    /**
     * Configures the iOS platform for the application module.
     *
     * Sets up iOS targets and frameworks for multiplatform support.
     */
    override fun Project.configureIOsPlatform() {
        with(libs) {
            extensions.getByType<KotlinMultiplatformExtension>().apply {
                androidTarget().apply {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "21"
                        }
                    }
                }
                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = moduleName
                        isStatic = true
                        export(project(path))
                    }
                }
            }
        }
    }

    /**
     * Configures common dependencies for all platforms.
     *
     * Adds Compose and other shared libraries to the common source set.
     *
     * @see <a href="https://slack-chats.kotlinlang.org/t/23173883/web-target-it-works-with-ios-android-desktop-but-now-my-wasm">Slack discussion</a>
     */
    override fun Project.configureCommonDependencies() {
        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain {
                    compilerOptions {
                        freeCompilerArgs.add("-Xcontext-receivers")
                    }
                }
                commonMain.dependencies {
                    implementation(composeDependencies.runtime)
                    implementation(composeDependencies.foundation)
                    implementation(composeDependencies.material)
                    implementation(composeDependencies.ui)
                    implementation(composeDependencies.components.uiToolingPreview)

                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                }
            }
        }
    }

}