plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.milad.datastore"
    compileSdk = 32
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.datastore)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)
}