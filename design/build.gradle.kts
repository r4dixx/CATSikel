plugins {
    alias(libs.plugins.cats.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.r4dixx.cats.design"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)
    lintChecks(libs.bundles.compose.lint)

    implementation(project(":core:ui"))
}
