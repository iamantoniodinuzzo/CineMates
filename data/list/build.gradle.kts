plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.list"

}

dependencies {
    api(project(":core:model:media_list"))
    api(project(":core:model:base"))
    implementation(project(":core:response"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(libs.bundles.androidX)
    testImplementation(libs.junit)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Dagger Hilt
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.compiler)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)
}