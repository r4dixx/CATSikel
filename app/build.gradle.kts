plugins {
    alias(libs.plugins.cats.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.r4dixx.cats"

    defaultConfig {
        applicationId = "com.r4dixx.cats"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isShrinkResources = true
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.koin.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    lintChecks(libs.bundles.compose.lint)

    implementation(libs.material)

    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":data:api"))
    implementation(project(":data:local"))
    implementation(project(":data:repository"))
    implementation(project(":design"))
    implementation(project(":domain"))
    implementation(project(":feature:account"))
    implementation(project(":feature:banks"))
}
