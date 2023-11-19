plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.movie"

}

dependencies {
    api(project(":core:model:base"))
    api(project(":core:model:common"))
    implementation(libs.bundles.androidX)
}
