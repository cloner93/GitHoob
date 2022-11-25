@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.milad.data"
    compileSdk = 32
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)

    implementation(libs.gson)
    implementation(libs.retrofit)

    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)

    implementation(libs.datastore)

}