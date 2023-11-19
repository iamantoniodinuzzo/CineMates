plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.common"
}

dependencies {
    implementation(project(":core:model:base"))
    implementation(libs.bundles.androidX)
}
