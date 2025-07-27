package dev.yamh.io.convention.source.plugin

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.core.Abi.getByName
import dev.yamh.io.convention.core.ext.libs
import dev.yamh.io.convention.core.ext.moduleName
import dev.yamh.io.convention.source.plugin.FeatureConventionPlugin
import dev.yamh.io.convention.source.plugin.base.BaseConventionPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Convention plugin for configuring library modules in a multiplatform project.
 *
 * Applies required plugins, configures Android and iOS targets, and sets up common dependencies.
 */
class LibraryConventionPlugin : BaseConventionPlugin() {

    /**
     * Applies the Kotlin Multiplatform and Android Library plugins to the project.
     */
    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("androidLibrary").get().get().pluginId)
    }

    /**
     * Configures the Android platform for the library module.
     *
     * Sets namespace, compile SDK, min SDK, target SDK, and Java compatibility.
     */
    override fun Project.configureAndroidPlatform() {
        val minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        val targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()

        extensions.getByType<LibraryExtension>().apply {
            namespace = moduleName
            compileSdk = targetSdk
            defaultConfig {
                this.minSdk = minSdk
                this.targetSdk = targetSdk
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
            buildFeatures {
                buildConfig = true
            }
        }
    }

    /**
     * Configures the iOS platform for the library module.
     *
     * Sets up iOS targets and configures framework binaries.
     */
    override fun Project.configureIOsPlatform() {
        with(libs) {
            extensions.getByType<KotlinMultiplatformExtension>().apply {
                // Configure Android target JVM options
                androidTarget().apply {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "21"
                        }
                    }
                }
                // Configure iOS targets and frameworks
                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = moduleName.replace(".", "-")
                        isStatic = true
                    }
                }
            }
        }
    }

    /**
     * Configures common dependencies for all platforms.
     *
     * Adds coroutines and lifecycle libraries to the commonMain source set.
     *
     * @see <a href="https://slack-chats.kotlinlang.org/t/23173883/web-target-it-works-with-ios-android-desktop-but-now-my-wasm">Slack discussion</a>
     */
    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            explicitApi()
            sourceSets.apply {
                commonMain {
                    compilerOptions {
                        freeCompilerArgs.add("-Xcontext-receivers")
                    }
                }
                commonMain.dependencies {
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                }
            }
        }
    }

}