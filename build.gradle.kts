// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()

        maven { url = uri("../githoob-prebuilts/m2repository") }
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.safearges) apply false
    alias(libs.plugins.android.kotlin) apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}