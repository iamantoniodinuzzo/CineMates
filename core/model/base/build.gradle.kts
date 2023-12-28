plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.base"
}

dependencies {
    testImplementation(libs.junitTest)
    implementation(libs.bundles.androidX)
}


