plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.movie_details"

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
    implementation(project(":data:genre"))
    implementation(project(":data:list"))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.legacy)
    implementation(libs.androidx.fragment)
    testImplementation(project(":core:testing"))
    testImplementation(libs.junitTest)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)
    androidTestImplementation(libs.bundles.androidTest)

    implementation(libs.core.ktx)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.timber)
}
