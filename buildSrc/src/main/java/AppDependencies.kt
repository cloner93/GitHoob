import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    //android ui base
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    private val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    private val activity = "androidx.activity:activity-ktx:${Versions.activity}"

    // jetpack navigation
    private val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private val navigationUi = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    // Coroutines
    private val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    private val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

    //  lifecycle
    private val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleScope}"
    private val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleScope}"
    private val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleScope}"
    private val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleScope_extensions}"
    private val lifecycleArch = "android.arch.lifecycle:extensions:${Versions.lifecycleArch}"

    //  network and gson
    private val gson ="com.google.code.gson:gson:${Versions.gson}"
    private val retrofit ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofitGson ="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val okhttpLogging="com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //  hilt
    private val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    private val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt_compiler}"
    private val hilt_lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle}"
    private val hilt_androidx = "androidx.hilt:hilt-compiler:${Versions.hilt_androidx}"

    // Glide
    private val glide="com.github.bumptech.glide:glide:${Versions.glide}"
    private val glide_compiler="com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
    private val glide_transformations="jp.wasabeef:glide-transformations:${Versions.glide_transformations}"

    private val datastore= "androidx.datastore:datastore-preferences:${Versions.datastore}"
    private val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private val markwon_core= "io.noties.markwon:core:${Versions.markwon}"
    private val markwon_html= "io.noties.markwon:html:${Versions.markwon}"
    private val markwon_image= "io.noties.markwon:image:${Versions.markwon}"


    val appLibraries = arrayListOf<String>().apply {
        add(appcompat)
        add(coreKtx)
        add(material)
        add(constraintLayout)
        add(recyclerview)
        add(legacy)
        add(activity)
        add(navigationFragment)
        add(navigationUi)
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(lifecycleViewmodel)
        add(lifecycleRuntime)
        add(lifecycleLivedata)
        add(lifecycleExtensions)
        add(lifecycleArch)
        add(gson)
        add(retrofit)
        add(retrofitGson)
        add(okhttpLogging)
        add(hilt_android)
        add(hilt_lifecycle)
        add(glide)
        add(glide_transformations)
        add(datastore)
        add(timber)
        add(markwon_core)
        add(markwon_html)
        add(markwon_image)
    }

    val annotationLibraries = arrayListOf<String>().apply {
        add(glide_compiler)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hilt_androidx)
        add(hilt_compiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}