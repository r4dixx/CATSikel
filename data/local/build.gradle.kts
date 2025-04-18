plugins {
    alias(libs.plugins.cats.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.r4dixx.cats.data.local"

    defaultConfig {
        buildConfigField("int", "DATABASE_VERSION", libs.versions.database.get())
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.koin.android)

    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
}
