package com.r4dixx.cats.buildLogic

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.setupAndroidModule(isApplication = false)
    }
}