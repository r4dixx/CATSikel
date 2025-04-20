import com.r4dixx.cats.buildLogic.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

// Necessary to put this here for Room to work correctly with Instant
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xopt-in=kotlin.time.ExperimentalTime")
    }
}

dependencies {
    implementation(libs.koin.android)

    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.room)
}
