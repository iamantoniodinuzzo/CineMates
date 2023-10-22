plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.indisparte.database"
    compileSdk= Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk

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