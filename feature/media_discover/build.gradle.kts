plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.media_discover"

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
    implementation(project(":data:discover"))
    implementation(project(":data:genre"))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.legacy)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junitTest)
    testImplementation(project(":core:testing"))
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)
    androidTestImplementation(libs.bundles.androidTest)

    implementation(libs.core.ktx)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
    implementation(libs.timber)
}
