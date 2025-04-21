plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.domain"
}

dependencies {
    implementation(libs.koin.android)
}
