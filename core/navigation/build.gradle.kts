plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.navigation"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.legacy)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.bundles.androidTest)

    // Jetpack Navigation - Kotlin
    implementation(libs.bundles.navigation)
}
