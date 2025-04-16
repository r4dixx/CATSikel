plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.r4dixx.cats.data.api"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdkMin.get().toInt()
        lint.targetSdk = libs.versions.sdkTarget.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.bundles.ktor)

    implementation(project(":core"))
    implementation(project(":domain"))
}
