plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.filter"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.bundles.androidX)
}
