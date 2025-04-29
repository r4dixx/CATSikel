plugins {
    alias(libs.plugins.cats.android.library)
}

android {
    namespace = "com.r4dixx.cats.core.utils"
}

dependencies {
    implementation(libs.kotzilla.sdk)
    implementation(libs.koin.android)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.koin.test)
}
