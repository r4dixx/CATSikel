plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.data.repository"
}

dependencies {
    implementation(libs.koin.android)

    implementation(project(":data:local"))
    implementation(project(":data:api"))
    implementation(project(":domain"))
}
