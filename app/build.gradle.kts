plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.r4dixx.cats"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        applicationId = "com.r4dixx.cats"
        minSdk = libs.versions.sdkMin.get().toInt()
        targetSdk = libs.versions.sdkTarget.get().toInt()
        versionCode = libs.versions.appMajor.get().toInt() * 10_000 + libs.versions.appMinor.get().toInt() * 100 + libs.versions.appPatch.get().toInt()
        versionName = "${libs.versions.appMajor}.${libs.versions.appMinor}.${libs.versions.appPatch}"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.androidx.activity.compose)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))
}
