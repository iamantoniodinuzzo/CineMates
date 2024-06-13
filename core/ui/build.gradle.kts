plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)

}
apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.ui"

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:network"))
    implementation(project(":core:model:person"))
    implementation(project(":core:model:movie"))
    implementation(project(":core:model:tv"))
    implementation(project(":core:model:media_list"))
    implementation(project(":core:navigation"))

//    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.material)
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.bundles.androidX)

    // Glide Image Loading Library for Android
    implementation(libs.glide.core)
    kapt(libs.glide.compiler)
    implementation(libs.coil)

}
