plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.testing"

}

dependencies {
    implementation(libs.junit)
    implementation(libs.coroutines.test)
    implementation(libs.junitAndroid)

    // Kotlin Extensions
    implementation(libs.androidx.core.ktx)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    //Timber
    implementation(libs.timber)
}
