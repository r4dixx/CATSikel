package com.r4dixx.cats.buildLogic

import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs
    get(): LibrariesForLibs = extensionOf(this, "libs") as LibrariesForLibs

fun PluginManager.alias(notation: Provider<PluginDependency>) {
    apply(notation.get().pluginId)
}

fun DependencyHandler.implementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", provider.get().group + ":" + provider.get().name + ":" + provider.get().version)
}

fun Project.setupAndroidModule(isApplication: Boolean) {
    with(pluginManager) {
        if (isApplication) {
            alias(libs.plugins.android.application)
        } else {
            alias(libs.plugins.android.library)
        }

        alias(libs.plugins.kotlin.android)
    }

    extensions.configure<BaseExtension> {
        compileSdkVersion(libs.versions.sdkCompile.get().toInt())

        defaultConfig {
            minSdk = libs.versions.sdkMin.get().toInt()
            targetSdk = libs.versions.sdkTarget.get().toInt()
            versionCode = libs.versions.appMajor.get().toInt() * 10_000 + libs.versions.appMinor.get().toInt() * 100 + libs.versions.appPatch.get().toInt()
            versionName = "${libs.versions.appMajor.get()}.${libs.versions.appMinor.get()}.${libs.versions.appPatch.get()}"
        }

        if (isApplication) {
            signingConfigs {
                create("release") {
                    storeFile = file(findProperty("CATSIKEL_KEYSTORE_FILE") as? String ?: "")
                    storePassword = findProperty("CATSIKEL_KEYSTORE_PASSWORD") as? String ?: ""
                    keyAlias = findProperty("CATSIKEL_KEYSTORE_KEY_ALIAS") as? String ?: ""
                    keyPassword = findProperty("CATSIKEL_KEYSTORE_KEY_PASSWORD") as? String ?: ""
                }
            }

            buildTypes {
                maybeCreate("debug")
                named("debug") {
                    isMinifyEnabled = false
                }
                maybeCreate("release")
                named("release") {
                    signingConfig = signingConfigs.getByName("release")
                    isMinifyEnabled = true
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
                }
            }
        }

        compileOptions {
            val javaVersion = JavaVersion.valueOf("VERSION_${libs.versions.java.get()}")
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                val javaVersion = JvmTarget.valueOf("JVM_${libs.versions.java.get()}")
                jvmTarget.set(javaVersion)
            }
        }
    }
}

fun Project.setupBaseDependencies() {
    dependencies {
        implementation(libs.square.logcat)
    }
}