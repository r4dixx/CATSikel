import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
}

group = "com.r4dixx.cats.buildLogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)

    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

gradlePlugin {
    plugins {
        register("moduleAndroidApplication") {
            id = "com.r4dixx.cats.android.application"
            implementationClass = "com.r4dixx.cats.buildLogic.AndroidApplicationModulePlugin"
        }
        register("moduleAndroidLibrary") {
            id = "com.r4dixx.cats.android.library"
            implementationClass = "com.r4dixx.cats.buildLogic.AndroidLibraryModulePlugin"
        }
    }
}
