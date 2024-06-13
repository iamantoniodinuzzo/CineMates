plugins {
    id(libs.plugins.android.library.get().pluginId)
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
//    id("androidx.navigation.safeargs.kotlin")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.safeargs.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.navigation"
}

dependencies {
   /* implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.legacy)
    implementation(libs.androidx.fragment)*/
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidTest)

    // Jetpack Navigation - Kotlin
    implementation(libs.bundles.navigation)
}
