@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.milad.common"
    compileSdk = 32

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.lifecycleRuntime)
    implementation(libs.lifecycleViewmodel)
}