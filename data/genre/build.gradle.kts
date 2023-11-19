plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.genre"

}

dependencies {
    implementation(project(":core:model:common"))
    implementation(project(":core:model:base"))
    implementation(project(":core:response"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(libs.bundles.androidX)
    testImplementation(libs.junitTest)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    // Timber
    implementation(libs.timber)
}
