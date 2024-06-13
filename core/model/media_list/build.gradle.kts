//@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.media_list"

}
dependencies {
//    implementation(libs.androidx.annotation.jvm)
    implementation(libs.bundles.androidX)

}
