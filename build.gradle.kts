// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.modulegraph) apply true
    alias(libs.plugins.ksp) apply false
}

moduleGraphConfig {
    readmePath.set("docs/README.md")
    heading.set("# Module Graph")
    setStyleByModuleType.set(true)
}
