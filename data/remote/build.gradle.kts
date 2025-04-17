plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.data.remote"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.bundles.ktor)

    implementation(project(":domain"))
}
