plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}
apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.util"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core:network"))

    implementation(libs.bundles.androidX)
    implementation(libs.material)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.bundles.androidTest)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Fragment
    implementation(libs.androidx.fragment)
}
