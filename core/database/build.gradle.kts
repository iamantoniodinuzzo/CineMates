plugins {
//    id ("com.android.library")
//    id ("org.jetbrains.kotlin.android")
//    id ("kotlin-kapt")
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.indisparte.database"
}

dependencies {

    implementation (project(":core:model:common"))
    implementation (project(":core:model:base"))
    implementation (project(":core:model:person"))

    implementation(libs.bundles.androidX)
    implementation(libs.bundles.coroutines)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Room
    implementation(libs.bundles.room)
    kapt(libs.androidx.room.compiler)

    // Dagger Hilt
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.compiler)

    //Timber
    implementation(libs.timber)
}