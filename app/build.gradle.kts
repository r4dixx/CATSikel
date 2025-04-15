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

    signingConfigs {
        create("release") {
            storeFile = file(findProperty("CATSIKEL_KEYSTORE_FILE") as? String ?: "")
            storePassword = findProperty("CATSIKEL_KEYSTORE_PASSWORD") as? String ?: ""
            keyAlias = findProperty("CATSIKEL_KEYSTORE_KEY_ALIAS") as? String ?: ""
            keyPassword = findProperty("CATSIKEL_KEYSTORE_KEY_PASSWORD") as? String ?: ""
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            signingConfig = signingConfigs.getByName("release")
        }
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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
    lintChecks(libs.bundles.compose.lint)

    implementation(project(":core"))
    implementation(project(":design"))
    implementation(project(":feature:account"))
    implementation(project(":feature:banks"))
}
