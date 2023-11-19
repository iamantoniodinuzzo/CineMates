plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}
apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.ui"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:network"))
    implementation(project(":core:model:person"))
    implementation(project(":core:model:movie"))
    implementation(project(":core:model:tv"))
    implementation(project(":core:model:media_list"))
    implementation(project(":core:navigation"))

    implementation(libs.bundles.androidX)
    implementation(libs.material)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.bundles.androidTest)

    // Glide Image Loading Library for Android
    implementation(libs.glide.core)
    kapt(libs.glide.compiler)
}
