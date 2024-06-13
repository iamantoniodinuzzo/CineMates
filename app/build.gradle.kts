plugins {

    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.safeargs.get().pluginId)
    id(libs.plugins.hilt.plugin.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)

}

android {
    compileSdk = Configuration.compileSdk
    namespace = "com.indisparte.cinemates"

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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

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

    implementation(libs.androidx.core.ktx)
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

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    // Jetpack Navigation - Kotlin
    implementation(libs.bundles.navigation)

    // Dagger Hilt
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Glide
    implementation(libs.glide.core)
    kapt(libs.glide.compiler)
    implementation(libs.coil)


    // Youtube
    //implementation files('libs/YouTubeAndroidPlayerApi.jar')
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")

    //Timber
    implementation(libs.timber)

    //LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")


}

