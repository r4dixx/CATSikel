plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.data.persistence"
}

dependencies {
    implementation(libs.koin.android)
}
