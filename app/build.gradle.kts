plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")

}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.indisparte.cinemates"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    namespace = "com.indisparte.cinemates"
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:saved"))
    implementation(project(":feature:movie_details"))
    implementation(project(":feature:media_search"))
    implementation(project(":feature:media_discover"))
    implementation(project(":feature:person_details"))
    implementation(project(":feature:list_creation"))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.legacy)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.bundles.androidTest)

    // Kotlin Extensions
    implementation(libs.core.ktx)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    // Jetpack Navigation - Kotlin
    implementation(libs.bundles.navigation)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)


    // Glide
    implementation(libs.glide.core)
    kapt(libs.glide.compiler)

    // Youtube
    //implementation files('libs/YouTubeAndroidPlayerApi.jar')
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")

    //Timber
    implementation(libs.timber)

    //LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")


}

