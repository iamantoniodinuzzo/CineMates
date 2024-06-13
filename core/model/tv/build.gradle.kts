plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.tv"

}

dependencies {
    api(project(":core:model:base"))
    api(project(":core:model:common"))
    implementation(libs.bundles.androidX)
}
