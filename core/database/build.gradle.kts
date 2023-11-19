plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
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
    androidTestImplementation(libs.bundles.androidTest)

    //Room
    implementation(libs.bundles.room)
    kapt(libs.androidx.room.compiler)
    testImplementation(libs.room.testing)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Timber
    implementation(libs.timber)
}