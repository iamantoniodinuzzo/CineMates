plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.testing"

}

dependencies {
    implementation(libs.junitTest)
    implementation(libs.coroutines.test)

    // Kotlin Extensions
    implementation(libs.core.ktx)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    //Timber
    implementation(libs.timber)
    implementation(libs.junitAndroid)
}
