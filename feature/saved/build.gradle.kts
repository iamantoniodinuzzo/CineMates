plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

apply<MainGradlePlugin>()


android {
    namespace = "com.indisparte.saved"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core:util"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))
    implementation(project(":data:movie"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.timber)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.junitAndroid)
    androidTestImplementation(libs.androidx.espresso)

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}