plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.safearges)
}

android {
    namespace = "com.milad.githoob"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.milad.githoob"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles = getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.data)
    implementation(projects.core.common)

    implementation(projects.feature.splash)

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.coroutinesCore)

    implementation(libs.legacy)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.extJUnit)
    androidTestImplementation(libs.espressoCore)

    implementation(libs.lifecycleViewmodel)
    implementation(libs.lifecycleRuntime)
    implementation(libs.lifecycleLivedata)
    implementation(libs.lifecycleExtensions)

    implementation(libs.activity)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okhttpLogging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)

    implementation(libs.glide)
    implementation(libs.glide.compiler)
    implementation(libs.glide.transformations)
    implementation(libs.datastore)
    implementation(libs.markwon.core)
    implementation(libs.markwon.html)
    implementation(libs.markwon.image)
    implementation(libs.timber)
}