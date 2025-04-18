plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.data.repository"
}

dependencies {
    implementation(libs.koin.android)

    implementation(project(":data:persistence"))
    implementation(project(":data:remote"))
    implementation(project(":domain"))
}
