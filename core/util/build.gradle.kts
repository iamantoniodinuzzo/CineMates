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
    namespace = "com.indisparte.util"

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
