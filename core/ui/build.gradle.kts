plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.indisparte.ui"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:network"))
    implementation(project(":core:model:person"))
    implementation(project(":core:model:movie"))
    implementation(project(":core:model:tv"))
    implementation(project(":core:model:list"))
    implementation(project(":core:navigation"))

    implementation(libs.bundles.androidX)
    implementation(libs.material)
    testImplementation(libs.junitTest)
    androidTestImplementation(libs.bundles.androidTest)

    // Glide Image Loading Library for Android
    implementation(libs.glide.core)
    kapt(libs.glide.compiler)
}
