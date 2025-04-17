plugins {
    alias(libs.plugins.cats.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.r4dixx.cats.feature.account"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.koin.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.kotlinx.collections.immutable)

    lintChecks(libs.bundles.compose.lint)

    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":design"))
    implementation(project(":domain"))
}
