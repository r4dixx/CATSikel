plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.cats.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "com.r4dixx.cats.data.api"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.ktorfit.plugin)
    implementation(libs.bundles.ktor)
}
