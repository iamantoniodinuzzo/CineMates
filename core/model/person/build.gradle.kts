plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.person"

}

dependencies {
    api(project(":core:model:base"))
    api(project(":core:model:common"))
    testImplementation(libs.junitTest)
    implementation(libs.bundles.androidX)
}
