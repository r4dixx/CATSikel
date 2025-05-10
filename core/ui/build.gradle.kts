plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.core.ui"
}

dependencies {
    implementation(libs.koin.android)
}
