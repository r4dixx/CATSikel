plugins {
    alias(libs.plugins.cats.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.r4dixx.cats.data.network"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.bundles.ktor)
}
