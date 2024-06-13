plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.filter"
}

dependencies {
    implementation(project(":core:ui"))
//    implementation(libs.bundles.androidX)
}
